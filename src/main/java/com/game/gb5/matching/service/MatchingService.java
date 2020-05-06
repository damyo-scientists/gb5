package com.game.gb5.matching.service;

import com.game.gb5.matching.dto.MatchingDto;
import com.game.gb5.matching.model.Matching;
import com.game.gb5.matching.repository.MatchingRepository;
import com.game.gb5.matching.utils.MatchMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class MatchingService {
    @Autowired
    MatchingService(MatchingRepository matchingRepository, MatchMaker matchMaker) {
        this.matchMaker = matchMaker;
        this.matchingRepository = matchingRepository;
    }

    private MatchMaker matchMaker;

    private MatchingRepository matchingRepository;

    public Optional<Matching> getById(long matchId) {
        return matchingRepository.findById(matchId);
    }

    public Optional<Matching> getByCode(String code) {
        return matchingRepository.findByCode(code);
    }

    public Matching create(MatchingDto matchingDto) {
        return this.matchingRepository.save(matchingDto.toEntity());
    }

    public Matching update(Matching matching) {
        return this.matchingRepository.save(matching);
    }

    public void delete(Matching matching) {
        this.matchingRepository.delete(matching);
    }

    @Async
    public CompletableFuture<List<Matching>> importData(List<MatchingDto> importMatchingDtos) {
        List<Matching> matchingList = importMatchingDtos.stream().map(dto -> {
            Optional<Matching> matchExisted = getByCode(dto.getCode());
            matchExisted.ifPresent(matchPresent -> dto.setIdAndCreatedDate(matchPresent.getId(), matchPresent.getCreatedDate()));
            Matching matchingEntity = matchMaker.toEntity(dto);
            if (dto.getCreatedDate() != null) {
                matchingEntity.setCreatedDate(dto.getCreatedDate());
            }
            return matchingEntity;
        }).collect(Collectors.toList());
        return CompletableFuture.completedFuture(matchingRepository.saveAll(matchingList));
    }
}
