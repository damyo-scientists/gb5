package com.game.gb5.model.character;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.game.gb5.model.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CharacterStatus extends BaseEntity {
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Character character;
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
