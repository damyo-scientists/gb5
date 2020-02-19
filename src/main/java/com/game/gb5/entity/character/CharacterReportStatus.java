package com.game.gb5.entity.character;

import java.io.Serializable;

import lombok.Data;

@Data
public class CharacterReportStatus implements Serializable {
	public String leadership;
	public String runningSpeed;
	public String reflexes;
	public String battingPower;
	public String battingAccuracy;
	public String sense;
	public String throwingSpeed;
	public String throwingAccuracy;
	public String defending;
	public String healthPoint;
	public String concentrationPoint;
	
	public String toReflectedValue(int minValue, int maxValue) {
		return minValue + "~" + maxValue;
	}
}
