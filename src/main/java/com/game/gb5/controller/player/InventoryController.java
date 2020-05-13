package com.game.gb5.controller.player;

import com.game.gb5.model.player.Inventory;
import com.game.gb5.service.inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventories")
public class InventoryController {
	private final InventoryService inventoryService;

	@Autowired
	public InventoryController(InventoryService inventoryService)  {
		this.inventoryService = inventoryService;
	}

	@GetMapping("/{player_id}")
	public ResponseEntity getInventoryByPlayerId(@PathVariable("player_id") final long playerId) {
		Inventory inventory = inventoryService.getInventoryByPlayerId(playerId);
		if (inventory != null) {
			return new ResponseEntity<>(inventory, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Inventory not found", HttpStatus.NOT_FOUND);
		}
	}
}
