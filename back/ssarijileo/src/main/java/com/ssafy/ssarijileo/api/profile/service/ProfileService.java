package com.ssafy.ssarijileo.api.profile.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ssafy.ssarijileo.api.profile.dto.ProfileDto;
import com.ssafy.ssarijileo.api.profile.dto.ProfileInfoDto;

public interface ProfileService {

	SseEmitter connection(String userId);

	void insertProfile(ProfileDto profileDto);

	ProfileInfoDto findProfileById(String userId);

	void updateProfile(ProfileInfoDto profileInfoDto);

	void updateImage(ProfileDto profileDto);

	boolean checkNickname(String nickname);
}
