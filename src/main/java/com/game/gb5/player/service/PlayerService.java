package com.game.gb5.player.service;

import com.game.gb5.inventory.repository.InventoryRepository;
import com.game.gb5.player.model.Player;
import com.game.gb5.player.repository.PlayerRepository;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {
	private final PlayerRepository playerRepository;
	private final InventoryRepository inventoryRepository;
	
	public PlayerService(PlayerRepository playerRepository, InventoryRepository inventoryRepository) {
		this.playerRepository = playerRepository;
		this.inventoryRepository = inventoryRepository;
	}
	
	public Player getPlayerById(Long id) {
		return playerRepository.getPlayerById(id);
	}
	
	public Player createPlayer(String userName, String userId) {
		Player player = new Player(userName, userId);
		return playerRepository.save(player);
	}
}
