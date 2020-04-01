package com.game.gb5.character.respository;

import com.game.gb5.character.model.CharacterStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CharacterStatusRepository extends JpaRepository<CharacterStatus, Long> {
}
