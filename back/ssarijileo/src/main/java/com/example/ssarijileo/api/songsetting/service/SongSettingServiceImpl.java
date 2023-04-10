package com.example.ssarijileo.api.songsetting.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.ssarijileo.api.songsetting.dto.SongSettingDto;
import com.example.ssarijileo.api.songsetting.entity.SongSetting;
import com.example.ssarijileo.api.songsetting.repository.SongSettingJpaRepository;
import com.example.ssarijileo.common.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class SongSettingServiceImpl implements SongSettingService{

	private final SongSettingJpaRepository songSettingJpaRepository;

	@Override
	public SongSettingDto findSongSettingByUserId(String userId) {
		return songSettingJpaRepository.findById(userId).orElseThrow(NotFoundException::new).toDto();
	}

	@Override
	public void updateSongSetting(SongSettingDto songSettingDto) {
		SongSetting songSetting = songSettingJpaRepository.findById(songSettingDto.getSongSettingId()).orElseThrow(NotFoundException::new);
		songSetting.updateSetting(songSettingDto.getEco(), songSettingDto.getVolume());
	}
}
