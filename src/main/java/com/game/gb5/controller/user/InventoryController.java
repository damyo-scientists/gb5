package com.game.gb5.controller.user;

import com.game.gb5.model.user.Inventory;
import com.game.gb5.service.inventory.InventoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventories")
@Tag(name = "Inventories")
public class InventoryController {
	private final InventoryService inventoryService;

	@Autowired
	public InventoryController(InventoryService inventoryService)  {
		this.inventoryService = inventoryService;
	}

	@GetMapping("/{user_id}")
	public ResponseEntity getInventoryByUserId(@PathVariable("user_id") final long userId) {
		Inventory inventory = inventoryService.getInventoryByUserId(userId);
		if (inventory != null) {
			return new ResponseEntity<>(inventory, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Inventory not found", HttpStatus.NOT_FOUND);
		}
	}
}
