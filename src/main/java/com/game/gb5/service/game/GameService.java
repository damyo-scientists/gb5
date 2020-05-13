package com.game.gb5.service.game;

import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.GameType;
import com.game.gb5.model.matching.Matching;
import com.game.gb5.repository.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game create(Matching matching) {
        Game game = Game.builder().gameType(GameType.VERSUS_AI).matching(matching).build();
        matching.setGame(game);
        game.setMatching(matching);
        return gameRepository.save(game);
    }
}
