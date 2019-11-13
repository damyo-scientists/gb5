package com.game.gb5.model.scouting;

import java.util.Date;

import lombok.Data;

@Data
public class ScouterStatus {
	private String deflection;
	private String deflectionRandomizeValue;
	private String reportTargetCharacterDatabase;
	private String reportChracterSize;
	private Date reportResetTime;
	private String reportRandomCount;
}
