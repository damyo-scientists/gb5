package com.game.gb5.character.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CharacterStatus extends BaseEntity {
	@JsonIgnore
	@OneToOne(mappedBy = "characterStatus")
	private GameCharacter character;
	@Column
	private int leadership;
	@Column
	private int runningSpeed;
	@Column
	private int reflexes;
	@Column
	private int battingPower;
	@Column
	private int battingAccuracy;
	@Column
	private int sense;
	@Column
	private int throwingSpeed;
	@Column
	private int throwingAccuracy;
	@Column
	private int defending;
	@Column
	private int healthPoint;
	@Column
	private int concentrationPoint;
}
