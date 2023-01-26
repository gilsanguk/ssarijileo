package com.ssafy.ssarijileo.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssarijileo.user.dto.Token;
import com.ssafy.ssarijileo.user.dto.UserDto;
import com.ssafy.ssarijileo.user.dto.UserRequestMapper;
import com.ssafy.ssarijileo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final UserRequestMapper userRequestMapper;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        UserDto userDto = userRequestMapper.toDto(oAuth2User);

        log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);
        // 최초 로그인이라면 회원가입 처리를 한다.
        log.info("find email = {}",userRepository.findByEmail(userDto.getEmail()));
        if (userRepository.findByEmail(userDto.getEmail()).equals(Optional.empty())) {
            log.info("회원가입 해야됨");
            userRepository.save(userDto.toUser(userDto));
        }


        String targetUrl;
        log.info("토큰 발행 시작");

        Token token = tokenProvider.generateToken(userDto.getEmail(), "ROLE_USER");
        log.info("{}", token);


        targetUrl = UriComponentsBuilder.fromUriString("/")
                .queryParam("accessToken", token.getAccessToken())
                .queryParam("refreshToken", token.getRefreshToken())
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);

       // writeTokenResponse(response, token);
    }

    /*
    private void writeTokenResponse(HttpServletResponse response, Token token)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        response.addHeader("Access", token.getAccessToken());
        response.addHeader("Refresh", token.getRefreshToken());
        response.setContentType("application/json;charset=UTF-8");

        var writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(token));
        writer.flush();
    }
    */
}
