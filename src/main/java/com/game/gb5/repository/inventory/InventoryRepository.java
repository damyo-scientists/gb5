package com.game.gb5.repository.inventory;

import com.game.gb5.model.player.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	Inventory getInventoryByPlayerId(Long id);
}
