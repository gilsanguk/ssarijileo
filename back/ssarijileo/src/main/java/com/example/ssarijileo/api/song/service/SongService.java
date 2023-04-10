package com.example.ssarijileo.api.song.service;

import java.util.List;

import com.example.ssarijileo.api.favoritesong.dto.FavoriteSongDto;
import com.example.ssarijileo.api.song.dto.SongDetailDto;
import com.example.ssarijileo.api.song.dto.SongDto;

public interface SongService {

	List<SongDto> findAllSong();

	List<SongDetailDto> findAllSongDetail();

	SongDetailDto findSongDetailById(Long songId);

	List<SongDto> findSongByUserId(String userId);

	String setFavoriteSong(FavoriteSongDto favoriteSongDto);
}
