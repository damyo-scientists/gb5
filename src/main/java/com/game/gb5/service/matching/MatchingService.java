package com.game.gb5.service.matching;

import com.game.gb5.dto.MatchingDto;
import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.result.GameResult;
import com.game.gb5.model.matching.Matching;
import com.game.gb5.repository.game.GameOptionsRepository;
import com.game.gb5.repository.game.GameRepository;
import com.game.gb5.repository.matching.MatchingRepository;
import com.game.gb5.service.game.GameService;
import com.game.gb5.utils.MatchMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class MatchingService {
    private MatchMaker matchMaker;
    private MatchingRepository matchingRepository;
    private GameRepository gameRepository;
    private GameOptionsRepository gameOptionsRepository;
    private GameService gameService;

    @Autowired
    public MatchingService(GameService gameService, MatchingRepository matchingRepository, GameRepository gameRepository, GameOptionsRepository gameOptionsRepository, MatchMaker matchMaker) {
        this.gameService = gameService;
        this.matchMaker = matchMaker;
        this.matchingRepository = matchingRepository;
        this.gameRepository = gameRepository;
        this.gameOptionsRepository = gameOptionsRepository;
    }

    public Optional<Matching> getById(long matchId) {
        return matchingRepository.findById(matchId);
    }

    public Optional<Matching> getByCode(String code) {
        return matchingRepository.findByCode(code);
    }

    public Matching create(MatchingDto matchingDto) {
        return this.matchingRepository.save(matchMaker.toEntity(matchingDto));
    }

    public GameResult starGame(Matching matching, Game game) {
        game.setMatching(matching);
        matching.setGame(game);
        matching.setOpened(true);

        matchingRepository.save(matching);
        game = gameRepository.save(game);
        gameOptionsRepository.save(game.getGameOptions());
        return gameService.startGame(game);
    }

    public void delete(Matching matching) {
        this.matchingRepository.delete(matching);
    }

    @Async
    public CompletableFuture<List<Matching>> importData(List<MatchingDto> importMatchingDtos) {
        List<Matching> matchingList = importMatchingDtos.stream().map(dto -> {
            Optional<Matching> matchingExisted = getByCode(dto.getCode());
            matchingExisted.ifPresent(matchPresent -> dto.setIdAndCreatedDate(matchPresent.getId(), matchPresent.getCreatedDate()));
            Matching matchingEntity = matchMaker.toEntity(dto);
            if (dto.getCreatedDate() != null) {
                matchingEntity.setCreatedDate(dto.getCreatedDate());
            }
            return matchingEntity;
        }).collect(Collectors.toList());
        return CompletableFuture.completedFuture(matchingRepository.saveAll(matchingList));
    }
}
