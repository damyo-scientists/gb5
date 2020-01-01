package com.game.gb5.service;

import com.game.gb5.dao.PlayerDao;
import com.game.gb5.domain.player.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
	@Autowired
	private PlayerDao playerDao;
	
	public Player getPlayerById(Long id) {
		return playerDao.getPlayerById(id);
	}
}
