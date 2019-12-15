package com.game.gb5.domain.character;

import com.game.gb5.domain.BaseEntity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class CharacterStatus extends BaseEntity {
	@OneToMany(fetch = FetchType.LAZY)
	private List<GameCharacter> characters;
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
