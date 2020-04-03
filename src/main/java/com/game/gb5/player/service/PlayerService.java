package com.game.gb5.player.service;

import com.game.gb5.inventory.repository.InventoryRepository;
import com.game.gb5.player.model.Player;
import com.game.gb5.player.repository.PlayerRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
	private final PlayerRepository playerRepository;
	private final InventoryRepository inventoryRepository;

	public PlayerService(PlayerRepository playerRepository, InventoryRepository inventoryRepository) {
		this.playerRepository = playerRepository;
		this.inventoryRepository = inventoryRepository;
	}

	public Player getById(Long id) {
		return playerRepository.getPlayerById(id);
	}

	public Player create(String userName, String userId) {
		Player player = new Player(userName, userId);
		return playerRepository.save(player);
	}

	public Player update(Player player) {
		return playerRepository.save(player);
	}

	public void delete(Player player) {
		playerRepository.delete(player);
	}
}
