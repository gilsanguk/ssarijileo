package com.example.ssarijileo.api.ranking.service;

import com.example.ssarijileo.api.ranking.dto.RankingDto;
import com.example.ssarijileo.api.ranking.dto.RankingType;

import java.util.List;

public interface RankingService {
    void setRanking(RankingType rankingType, List<RankingDto> list);
    List<RankingDto> getRanking(RankingType rankingType);
}
