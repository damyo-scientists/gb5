package com.game.gb5.dao;

import com.game.gb5.domain.character.CharacterStatus;

import org.springframework.data.repository.CrudRepository;

public interface CharacterStatusDao extends CrudRepository<CharacterStatus, Long> {
}
