package com.game.gb5.scouter.repository;

import com.game.gb5.scouter.model.Scouter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScouterRepository extends JpaRepository<Scouter, Long> {
	public Scouter getScouterById(Long id);
}
