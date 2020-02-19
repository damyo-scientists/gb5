package com.game.gb5.dao;

import com.game.gb5.inventory.model.entity.consumable.CharacterPiece;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterPieceDao extends CrudRepository<CharacterPiece, Long> {
}
