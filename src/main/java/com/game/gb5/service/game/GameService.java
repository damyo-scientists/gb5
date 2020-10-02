package com.game.gb5.service.game;

import com.game.gb5.model.game.Game;
import com.game.gb5.model.game.result.GameResult;
import com.game.gb5.model.game.result.InningResult;
import com.game.gb5.repository.game.GameOptionsRepository;
import com.game.gb5.repository.game.GameRepository;
import com.game.gb5.simulation.system.GameSystem;
import com.game.gb5.utils.Score;
import com.game.gb5.utils.ScoreCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private GameSystem gameSystem;

    @Autowired
    public GameService(GameSystem gameSystem) {
        this.gameSystem = gameSystem;
    }

    public GameResult startGame(Game game) {
        List<InningResult> inningResultList = gameSystem.start(game);
        Score score = ScoreCalculator.calcScore(inningResultList);
        return GameResult.builder().inningResults(inningResultList)
                .deck1Score(score.getDeck1Score())
                .deck2Score(score.getDeck2Score())
                .build();
    }
}
