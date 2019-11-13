package com.game.gb5.model.character;

import lombok.Data;

@Data
public class Character {
	private String name;
	private int rate;
	private int acquisitionCoefficient;
	private int cumulativeAcquisitionCoefficient;
	private int level;
	private int exp;
	private int backNumber;
	private CharacterStatus characterStatus;
}
