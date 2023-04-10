package com.example.ssarijileo.api.singingcontest.service;

import java.util.List;

import com.example.ssarijileo.api.singingcontest.dto.LikeDto;
import com.example.ssarijileo.api.singingcontest.dto.SingingContestResponseDto;
import com.example.ssarijileo.api.singingcontest.dto.SingingContestUpdateDto;

public interface SingingContestService {

	List<SingingContestResponseDto> findAllSingingContest(String userId);

	List<SingingContestResponseDto> findSingingContestByUserId(String userId);

	void insertSingingContest(Long recordingId);

	void updateSingingContest(SingingContestUpdateDto singingContestUpdateDto);

	Long setLike(LikeDto likeDto);
}
