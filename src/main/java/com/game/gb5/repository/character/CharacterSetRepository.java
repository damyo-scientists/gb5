package com.game.gb5.repository.character;

import com.game.gb5.model.character.CharacterSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterSetRepository extends JpaRepository<CharacterSet, Long> {
	public CharacterSet getCharacterSetById(Long id);
}
