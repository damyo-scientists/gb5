package com.game.gb5.scouter.repository;

import com.game.gb5.scouter.model.entity.report.ReportCharacter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCharacterRepository extends JpaRepository<ReportCharacter, Long> {
}
