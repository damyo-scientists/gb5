package com.game.gb5.repository;

import com.game.gb5.model.item.CharacterPiece;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterPieceRepository extends JpaRepository<CharacterPiece, Long> {
}
