package com.game.gb5.character.respository;

import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.inventory.model.item.CharacterPiece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<GameCharacter, Long> {
}
