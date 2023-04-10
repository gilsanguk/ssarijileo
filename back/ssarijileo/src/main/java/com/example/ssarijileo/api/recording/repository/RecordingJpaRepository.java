package com.example.ssarijileo.api.recording.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ssarijileo.api.recording.entity.Recording;

@Repository
public interface RecordingJpaRepository extends JpaRepository<Recording, Long> {

	Optional<List<Recording>> findByProfile_ProfileIdAndStatus(String userId, String status);
}
