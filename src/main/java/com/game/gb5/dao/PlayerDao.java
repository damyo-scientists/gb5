package com.game.gb5.dao;

import com.game.gb5.domain.player.Player;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDao extends CrudRepository<Player, Long> {
	public Player getPlayerById(Long id);
}
