package com.example.ssarijileo.api.songsetting.service;

import com.example.ssarijileo.api.songsetting.dto.SongSettingDto;
import com.example.ssarijileo.api.songsetting.entity.SongSetting;

public interface SongSettingService {

	SongSettingDto findSongSettingByUserId(String userId);

	void updateSongSetting(SongSettingDto songSettingDto);
}
