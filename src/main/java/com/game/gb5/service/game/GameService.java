package com.game.gb5.service.game;

import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.config.GameType;
import com.game.gb5.model.game.result.GameResult;
import com.game.gb5.model.game.result.InningResult;
import com.game.gb5.model.matching.Matching;
import com.game.gb5.repository.game.GameRepository;
import com.game.gb5.simulation.system.GameSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private GameRepository gameRepository;
    private GameSystem gameSystem;

    @Autowired
    public GameService(GameRepository gameRepository, GameSystem gameSystem) {
        this.gameRepository = gameRepository;
        this.gameSystem = gameSystem;
    }

    @Async
    public GameResult startGame(Matching matching) {
        Game game = Game.builder().gameType(GameType.VERSUS_AI).matching(matching).build();
        matching.setGame(game);
        game.setMatching(matching);
        gameRepository.save(game);
        List<InningResult> inningResultList = gameSystem.start(game);
        return GameResult.builder().inningResults(inningResultList).build();
    }
}
