package com.game.gb5.domain.character;

import javax.persistence.Entity;

import lombok.Data;

@Entity
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
	private CharacterStatusReport characterStatusReport;
}
