package com.example.ssarijileo.api.user.client;

import com.example.ssarijileo.api.user.dto.ProfileDto;
import com.example.ssarijileo.common.model.BaseResponseBody;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-profile-client", url = "192.168.49.2:31000/api/v1/profile")
public interface UserProfileClient {
    @PostMapping(produces = "application/json")
    ResponseEntity<? extends BaseResponseBody> insertProfile(@RequestBody ProfileDto profileDto);

    @PostMapping(value = "/image", produces = "application/json")
    ResponseEntity<? extends BaseResponseBody> updateImage(@RequestBody ProfileDto profileDto);
}
