package com.game.gb5.repository.game;

import com.game.gb5.model.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
