package com.example.ssarijileo.api.profile.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.ssarijileo.api.favoritesong.service.FavoriteSongService;
import com.example.ssarijileo.api.profile.dto.ProfileDto;
import com.example.ssarijileo.api.profile.dto.ProfileInfoDto;
import com.example.ssarijileo.api.profile.entitiy.Profile;
import com.example.ssarijileo.api.profile.repository.ProfileJpaRepository;
import com.example.ssarijileo.api.song.service.SongService;
import com.example.ssarijileo.api.songsetting.entity.SongSetting;
import com.example.ssarijileo.api.songsetting.repository.SongSettingJpaRepository;
import com.example.ssarijileo.common.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {

	private final ProfileJpaRepository profileJpaRepository;
	private final SongSettingJpaRepository songSettingJpaRepository;
	private final FavoriteSongService favoriteSongService;

	@Override
	public String findIdByNickname(String nickname) {
		Profile profile = profileJpaRepository.findByNickname(nickname).orElseThrow(NotFoundException::new);
		return profile.getProfileId();
	}

	@Override
	public void insertProfile(ProfileDto profileDto) {
		Profile profile = Profile.builder().profileDto(profileDto).build();
		profileJpaRepository.save(profile);

		SongSetting songSetting = SongSetting.builder().userId(profileDto.getProfileId()).build();
		songSettingJpaRepository.save(songSetting);
	}

	@Override
	public ProfileInfoDto findProfileById(String userId) {
		// Redis에 애창곡 정보 warm up
		favoriteSongService.getFavoriteSongFromDB(userId);
		return profileJpaRepository.findById(userId).orElseThrow(NotFoundException::new).toDto();
	}

	@Override
	public void updateProfile(ProfileInfoDto profileInfoDto) {
		Profile profile = profileJpaRepository.findById(profileInfoDto.getProfileId())
			.orElseThrow(NotFoundException::new);
		profile.updateNickname(profileInfoDto.getNickname());
		profileJpaRepository.save(profile);

		SongSetting songSetting = songSettingJpaRepository.findById(profileInfoDto.getProfileId())
			.orElseThrow(NotFoundException::new);
		songSetting.updateSetting(profileInfoDto.getEco(), profileInfoDto.getVolume());
		songSettingJpaRepository.save(songSetting);
	}

	@Override
	public void updateImage(ProfileDto profileDto) {
		Profile profile = profileJpaRepository.findById(profileDto.getProfileId()).orElseThrow(NotFoundException::new);
		profile.updateImage(profile.getImage());
		profileJpaRepository.save(profile);
	}

	@Override
	public boolean checkNickname(String nickname) {
		Profile profile = new Profile();
		// 비었다면 이름 변경 가능 -> true
		Profile profile2 = profileJpaRepository.findByNickname(nickname).orElse(profile);
		return profile.equals(profile2);
	}
}
