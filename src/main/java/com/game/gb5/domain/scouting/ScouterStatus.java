package com.game.gb5.domain.scouting;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ScouterStatus {
	private int deflection;
	private int deflectionRandomizeValue;
	private String reportTargetCharacterDatabase;
	private int reportChracterSize;
	private Date reportResetTime;
	private String reportRandomCount;
	private List<Integer> gradeAcquisitionRate;
	private List<Integer> reportHideStatusColumnList;
	private boolean isGiveMinimumGradeAssured;
}
