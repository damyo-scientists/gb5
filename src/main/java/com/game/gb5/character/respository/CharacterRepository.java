package com.game.gb5.character.respository;

import com.game.gb5.character.model.GameCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<GameCharacter, Long> {
    Optional<GameCharacter> findByCode(String code);
}
