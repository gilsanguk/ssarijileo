package com.example.ssarijileo.api.song.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.example.ssarijileo.api.favoritesong.service.FavoriteSongService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.ssarijileo.api.favoritesong.dto.FavoriteSongDto;
import com.example.ssarijileo.api.song.dto.SongDto;
import com.example.ssarijileo.api.favoritesong.entity.FavoriteSong;
import com.example.ssarijileo.api.favoritesong.repository.FavoriteSongJpaRepository;
import com.example.ssarijileo.api.song.repository.SongRepository;
import com.example.ssarijileo.common.exception.NotFoundException;
import com.example.ssarijileo.api.song.dto.SongDetailDto;
import com.example.ssarijileo.api.song.entity.Song;
import com.example.ssarijileo.api.song.repository.SongJpaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

	private final SongJpaRepository songJpaRepository;
	private final SongRepository songRepository;
	private final FavoriteSongJpaRepository favoriteSongJpaRepository;
	private final FavoriteSongService favoriteSongService;

	@Override
	public List<SongDto> findAllSong() {
		return songJpaRepository.findAll().stream().map(Song::toDto).collect(Collectors.toList());
	}

	@Override
	public List<SongDetailDto> findAllSongDetail() {
		return songJpaRepository.findAll().stream().map(Song::toDetailDto).collect(Collectors.toList());
	}

	@Override
	public SongDetailDto findSongDetailById(Long songId) {
		return songJpaRepository.findById(songId).orElseThrow(NotFoundException::new).toDetailDto();
	}

	@Override
	public List<SongDto> findSongByUserId(String userId) {
		return songRepository.findFavoriteSongByUserId(userId).orElseThrow(NotFoundException::new);
	}

	@Override
	public String setFavoriteSong(FavoriteSongDto favoriteSongDto) {
		switch(favoriteSongDto.getIsLike()) {
			case "Y" : favoriteSongService.subscribe(favoriteSongDto.getUserId(), favoriteSongDto.getSongId()); break;
			case "N" : favoriteSongService.unsubscribe(favoriteSongDto.getUserId(), favoriteSongDto.getSongId()); break;
			default : break;
		}
		return favoriteSongService.getUsersFavoriteSong(favoriteSongDto.getUserId()).toString();
	}
}
