package com.game.gb5.dao;

import com.game.gb5.domain.character.GameCharacter;

import org.springframework.data.repository.CrudRepository;

public interface GameCharacterDao extends CrudRepository<GameCharacter, Long> {
}
