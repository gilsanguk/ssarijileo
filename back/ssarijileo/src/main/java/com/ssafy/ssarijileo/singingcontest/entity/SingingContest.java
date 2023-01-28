package com.ssafy.ssarijileo.singingcontest.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.ssarijileo.recording.entity.Recording;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SingingContest {

	// PK (AUTO_INCREMENT)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long SingingContestId;

	// 녹화PK
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recording_id")
	Recording recording;

	// 등록일시
	String registerDate;

	// 상태(V:노출,D:삭제,B:신고)
	char status;
}
