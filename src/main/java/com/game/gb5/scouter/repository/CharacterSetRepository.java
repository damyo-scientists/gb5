package com.game.gb5.scouter.repository;

import com.game.gb5.character.model.CharacterSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterSetRepository extends JpaRepository<CharacterSet, Long> {
	public CharacterSet getCharacterSetById(Long id);
}
