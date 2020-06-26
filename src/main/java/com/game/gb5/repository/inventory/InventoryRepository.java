package com.game.gb5.repository.inventory;

import com.game.gb5.model.user.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	Inventory getInventoryByUserId(Long id);
}
