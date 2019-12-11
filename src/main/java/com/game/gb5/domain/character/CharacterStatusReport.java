package com.game.gb5.domain.character;

import lombok.Data;

@Data
public class CharacterStatusReport extends CharacterStatus {
	private String leadership;
	private String runningSpeed;
	private String reflexes;
	private String battingPower;
	private String battingAccuracy;
	private String sense;
	private String throwingSpeed;
	private String throwingAccuracy;
	private String defending;
	private String healthPoint;
	private String concentrationPoint;
	
	public String toReflectedValue(int minValue, int maxValue) {
		return minValue + "~" + maxValue;
	}
}
