package com.game.gb5.scouter.repository;

import com.game.gb5.scouter.model.report.ScoutingReport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoutingReportDao extends JpaRepository<ScoutingReport, Long> {
	public ScoutingReport getScoutingReportById(Long id);
}
