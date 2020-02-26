package com.game.gb5.inventory.repository;

import com.game.gb5.inventory.model.entity.inventory.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	Inventory getInventoryByPlayerId(Long id);
}
