package com.game.gb5.repository;

import com.game.gb5.model.character.CharacterStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterStatusRepository extends JpaRepository<CharacterStatus, Long> {
}
