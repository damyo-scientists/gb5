package com.game.gb5.domain.character;

public class CharacterStatusReport extends CharacterStatus {
	private static final long serialVersionUID = -8528567047493350550L;
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
