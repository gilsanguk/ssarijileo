package com.example.ssarijileo.api.song.repository;

import com.example.ssarijileo.api.song.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongJpaRepository extends JpaRepository<Song, Long> {
}
