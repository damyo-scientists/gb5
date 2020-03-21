package com.game.gb5.character.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
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
	private int sense;
	@Column
	private int battingPower;
	@Column
	private int battingAccuracy;
	@Column
    private int reflexes;
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

    public CharacterStatus(int leadership, int runningSpeed, int sense, int battingPower, int battingAccuracy,
						   int reflexes, int throwingSpeed, int throwingAccuracy, int defending, int healthPoint,
						   int concentrationPoint) {
    	this.leadership = leadership;
    	this.runningSpeed = runningSpeed;
		this.sense = sense;
		this.battingPower = battingPower;
    	this.battingAccuracy = battingAccuracy;
		this.reflexes = reflexes;
		this.throwingSpeed = throwingSpeed;
		this.throwingAccuracy = throwingAccuracy;
		this.defending = defending;
		this.healthPoint = healthPoint;
		this.concentrationPoint = concentrationPoint;
	}
}
