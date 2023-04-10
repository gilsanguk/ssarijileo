package com.example.ssarijileo.api.recording.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.ssarijileo.api.recording.dto.RecordingDto;
import com.example.ssarijileo.api.recording.dto.RecordingResponseDto;

public interface RecordingService {

	List<RecordingResponseDto> findRecordingByUserId(String userId);

	void insertRecording(RecordingDto recordingDto, MultipartFile file);

	void deleteRecording(Long recordingId);
}
