package com.ssafy.ssarijileo.recording.service;

import java.util.List;

import com.ssafy.ssarijileo.recording.dto.RecordingDto;

public interface RecordingService {

	List<RecordingDto> findAllRecording();

	RecordingDto findRecordingById(Long id);

	List<RecordingDto> findRecordingByUserId(String userId);

	void insertRecording(RecordingDto recordingDto);
}
