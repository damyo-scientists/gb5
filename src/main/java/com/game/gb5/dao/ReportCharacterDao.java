package com.game.gb5.dao;

import com.game.gb5.domain.scouting.report.ReportCharacter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCharacterDao extends CrudRepository<ReportCharacter, Long> {
}
