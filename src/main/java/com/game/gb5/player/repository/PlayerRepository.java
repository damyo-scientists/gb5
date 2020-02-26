package com.game.gb5.player.repository;

import com.game.gb5.player.model.entity.player.Player;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
	public Player getPlayerById(Long id);
}
