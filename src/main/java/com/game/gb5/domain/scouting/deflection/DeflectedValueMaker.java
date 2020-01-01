package com.game.gb5.domain.scouting.deflection;

import java.util.Random;

public class DeflectedValueMaker {
	private Random rand = new Random();
	
	public DeflectedValue changeBaseStatusToDeflectedValue(int baseStatus, int scouterDeflection, int scouterDeflectionRandomizeValue) {
		int firstRandomDeflection = rand.nextInt(scouterDeflection);
		int maxValue = baseStatus + firstRandomDeflection;
		if (maxValue > 100) {
			maxValue = 100;
		}
		
		int secondRandomDeflection = scouterDeflection - firstRandomDeflection + rand.nextInt(scouterDeflectionRandomizeValue);
		int minValue = baseStatus - secondRandomDeflection;
		
		if (minValue < 0) {
			minValue = 0;
		}
		
		return new DeflectedValue(minValue, maxValue);
	}
	
	private String toReflectedValue(int minValue, int maxValue) {
		return minValue + "~" + maxValue;
	}
}