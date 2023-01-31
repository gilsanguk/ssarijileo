package com.ssafy.ssarijileo.recording.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.ssarijileo.recording.dto.RecordingDto;
import com.ssafy.ssarijileo.song.entity.Song;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Recording {

	// PK (AUTO_INCREMENT)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long recordingId;

	// 사용자PK
	String userId;

	// 노래PK
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "song_id")
	Song song;

	// 녹화파일
	String file;

	// 녹화일시
	String registerDate;

	// Dto to Entity
	@Builder
	public Recording(RecordingDto recordingDto, Song song) {
		this.recordingId = recordingDto.getRecordingId();
		this.userId = recordingDto.getUserId();
		this.song = song;
		this.file = recordingDto.getFile();
		this.registerDate = recordingDto.getRegisterDate();
	}

	// Entity to Dto
	public RecordingDto toDto(){
		return new RecordingDto(recordingId, userId, song.getSongId(), file, registerDate);
	}
}
