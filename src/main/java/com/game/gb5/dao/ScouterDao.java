package com.game.gb5.dao;

import com.game.gb5.scouter.model.entity.Scouter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScouterDao extends CrudRepository<Scouter, Long> {
	public Scouter getScouterById(Long id);
}
