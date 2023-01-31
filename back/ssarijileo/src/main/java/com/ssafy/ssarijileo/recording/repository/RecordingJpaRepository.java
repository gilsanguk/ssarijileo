package com.ssafy.ssarijileo.recording.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssarijileo.recording.entity.Recording;

@Repository
public interface RecordingJpaRepository extends JpaRepository<Recording, Long> {

	Optional<List<Recording>> findRecordingByUserId(String userId);
}
