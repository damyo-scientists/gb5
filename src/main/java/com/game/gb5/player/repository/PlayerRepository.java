package com.game.gb5.player.repository;

import com.game.gb5.player.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    public Player getPlayerById(Long id);

    public Optional<Player> findByCode(String code);
}
