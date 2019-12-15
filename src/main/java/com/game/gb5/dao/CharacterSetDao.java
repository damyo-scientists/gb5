package com.game.gb5.dao;

import com.game.gb5.domain.character.CharacterSet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterSetDao extends CrudRepository<CharacterSet, Long> {
	public CharacterSet getCharacterSetById(Long id);
}
