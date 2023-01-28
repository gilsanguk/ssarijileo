package com.ssafy.ssarijileo.song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssarijileo.song.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
