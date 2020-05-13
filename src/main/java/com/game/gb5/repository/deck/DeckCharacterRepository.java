package com.game.gb5.repository.deck;

import com.game.gb5.model.deck.DeckCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckCharacterRepository extends JpaRepository<DeckCharacter, Long> {
}
