package com.example.ssarijileo.api.recording.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.querydsl.core.types.Projections;
import com.example.ssarijileo.api.file.service.FileService;
import com.example.ssarijileo.api.profile.entitiy.Profile;
import com.example.ssarijileo.api.profile.repository.ProfileJpaRepository;
import com.example.ssarijileo.api.recording.dto.RecordingResponseDto;
import com.example.ssarijileo.common.exception.NotFoundException;
import com.example.ssarijileo.api.recording.dto.RecordingDto;
import com.example.ssarijileo.api.recording.entity.Recording;
import com.example.ssarijileo.api.recording.repository.RecordingJpaRepository;
import com.example.ssarijileo.api.song.entity.Song;
import com.example.ssarijileo.api.song.repository.SongJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordingServiceImpl implements RecordingService {

	private final RecordingJpaRepository recordingJpaRepository;
	private final SongJpaRepository songJpaRepository;
	private final ProfileJpaRepository profileJpaRepository;

	private final FileService fileService;

	@Override
	public List<RecordingResponseDto> findRecordingByUserId(String userId) {
		return recordingJpaRepository.findByProfile_ProfileIdAndStatus(userId, "V")
			.orElseThrow(NotFoundException::new)
			.stream()
			.map(Recording::toDto)
			.collect(Collectors.toList());
	}

	@Override
	public void insertRecording(RecordingDto recordingDto, MultipartFile file) {
		// S3 파일 업로드
		recordingDto.setFile(fileService.fileUpload(file));

		Profile profile = profileJpaRepository.findById(recordingDto.getUserId()).orElseThrow(NotFoundException::new);
		Song song = songJpaRepository.findById(recordingDto.getSongId()).orElseThrow(NotFoundException::new);
		Recording recording = Recording.builder().recordingDto(recordingDto).profile(profile).song(song).build();
		recordingJpaRepository.save(recording);
	}

	@Override
	public void deleteRecording(Long recordingId) {
		Recording recording = recordingJpaRepository.findById(recordingId).orElseThrow(NotFoundException::new);
		recording.updateStatus("X");
	}
}
