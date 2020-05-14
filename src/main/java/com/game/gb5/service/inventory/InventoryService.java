package com.game.gb5.service.inventory;

import com.game.gb5.model.player.Inventory;
import com.game.gb5.repository.inventory.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
	private final InventoryRepository inventoryRepository;

	@Autowired
	public InventoryService(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	public Inventory getInventoryByPlayerId(Long playerId) {
		return inventoryRepository.getInventoryByPlayerId(playerId);
	}
}