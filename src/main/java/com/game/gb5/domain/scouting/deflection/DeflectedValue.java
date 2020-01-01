package com.game.gb5.domain.scouting.deflection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeflectedValue {
	private int minValue;
	private int maxValue;
	
	public String toString() {
		return minValue + "~" + maxValue;
	}
}
