package com.game.gb5.match.service;

import com.game.gb5.match.dto.MatchDto;
import com.game.gb5.match.model.Match;
import com.game.gb5.match.repository.MatchRepository;
import com.game.gb5.match.utils.MatchMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class MatchService {
    @Autowired
    MatchService(MatchRepository matchRepository, MatchMaker matchMaker) {
        this.matchMaker = matchMaker;
        this.matchRepository = matchRepository;
    }

    private MatchMaker matchMaker;

    private MatchRepository matchRepository;

    public Optional<Match> getById(long matchId) {
        return matchRepository.findById(matchId);
    }

    public Optional<Match> getByCode(String code) {
        return matchRepository.findByCode(code);
    }

    public Match create(MatchDto matchDto) {
        return this.matchRepository.save(matchDto.toEntity());
    }

    public Match update(Match match) {
        return this.matchRepository.save(match);
    }

    public void delete(Match match) {
        this.matchRepository.delete(match);
    }

    @Async
    public CompletableFuture<List<Match>> importData(List<MatchDto> importMatchDtos) {
        List<Match> matches = importMatchDtos.stream().map(dto -> {
            Optional<Match> matchExisted = getByCode(dto.getCode());
            matchExisted.ifPresent(matchPresent -> dto.setIdAndCreatedDate(matchPresent.getId(), matchPresent.getCreatedDate()));
            Match matchEntity = matchMaker.toEntity(dto);
            if (dto.getCreatedDate() != null) {
                matchEntity.setCreatedDate(dto.getCreatedDate());
            }
            return matchEntity;
        }).collect(Collectors.toList());
        return CompletableFuture.completedFuture(matchRepository.saveAll(matches));
    }
}
