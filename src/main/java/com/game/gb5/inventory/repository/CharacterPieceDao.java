package com.game.gb5.inventory.repository;

import com.game.gb5.inventory.model.entity.consumable.CharacterPiece;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterPieceDao extends JpaRepository<CharacterPiece, Long> {
}
