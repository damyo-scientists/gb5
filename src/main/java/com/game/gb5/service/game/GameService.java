package com.game.gb5.service.game;

import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.result.GameResult;
import com.game.gb5.model.game.result.InningResult;
import com.game.gb5.repository.game.GameOptionsRepository;
import com.game.gb5.repository.game.GameRepository;
import com.game.gb5.simulation.system.GameSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private GameRepository gameRepository;
    private GameOptionsRepository gameOptionsRepository;
    private GameSystem gameSystem;

    @Autowired
    public GameService(GameRepository gameRepository, GameOptionsRepository gameOptionsRepository, GameSystem gameSystem) {
        this.gameRepository = gameRepository;
        this.gameOptionsRepository = gameOptionsRepository;
        this.gameSystem = gameSystem;
    }

    public GameResult startGame(Game game) {
        List<InningResult> inningResultList = gameSystem.start(game);
        return GameResult.builder().inningResults(inningResultList).build();
    }
}
