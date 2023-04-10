package com.example.ssarijileo.api.song.repository;

import java.util.List;
import java.util.Optional;

import com.example.ssarijileo.api.song.dto.SongDto;

public interface SongRepository {

	Optional<List<SongDto>> findFavoriteSongByUserId(String userId);
}
