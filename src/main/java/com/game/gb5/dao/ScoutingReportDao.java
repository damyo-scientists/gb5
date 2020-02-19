package com.game.gb5.dao;

import com.game.gb5.entity.scouting.report.ScoutingReport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoutingReportDao extends CrudRepository<ScoutingReport, Long> {
	public ScoutingReport getScoutingReportById(Long id);
}
