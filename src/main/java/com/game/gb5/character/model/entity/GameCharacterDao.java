package com.game.gb5.character.model.entity;

import com.game.gb5.entity.character.GameCharacter;

import org.springframework.data.repository.CrudRepository;

public interface GameCharacterDao extends CrudRepository<GameCharacter, Long> {
}
