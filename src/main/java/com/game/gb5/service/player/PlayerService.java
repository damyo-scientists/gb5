package com.game.gb5.service.player;

import com.game.gb5.repository.inventory.InventoryRepository;
import com.game.gb5.model.player.Player;
import com.game.gb5.repository.player.PlayerRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<Player> getByCode(String code) {
		return playerRepository.findByCode(code);
    }
}
