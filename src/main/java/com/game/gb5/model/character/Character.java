package com.game.gb5.model.character;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.model.HittingPosition;
import com.game.gb5.model.Player;
import com.game.gb5.model.common.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity(name = "gameCharacter")
public class Character extends BaseEntity implements Cloneable {
    @Column
    private String name;
    @Column
    private int grade;
    @Column
    private int acquisitionCoefficient;
    @Column
    private int cumulativeAcquisitionCoefficient;
    @Column
    private int backNumber;
    @Transient
    @Enumerated(EnumType.STRING)
    private HittingPosition hittingPosition;
    @ElementCollection
    @Column
    private List<Float> hittingInclination;
    @JsonIgnore
    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private CharacterStatus characterStatus;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "characters")
    private List<CharacterSet> characterSets;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "characters")
    private List<Player> players;

    /**
     * Server Only
     */
    @Column
    private int acquisitionNumber;
    @Column
    private int level;
    @Column
    private int exp;
    @Column
    private boolean isCharacterLocked;
    private Date characterUnlockDateTime;

    public Character(Long id, String name, int grade, int acquisitionCoefficient, int cumulativeAcquisitionCoefficient,
                     int backNumber, HittingPosition hittingPosition, List<Float> hittingInclination, CharacterStatus characterStatus) {
        this(name, grade, acquisitionCoefficient, cumulativeAcquisitionCoefficient, backNumber, hittingPosition, hittingInclination, characterStatus);
        this.setId(id);
    }

    public Character(Long id, String code, String name, int grade, int acquisitionCoefficient, int cumulativeAcquisitionCoefficient,
                     int backNumber, HittingPosition hittingPosition, List<Float> hittingInclination, CharacterStatus characterStatus) {
        this(id, name, grade, acquisitionCoefficient, cumulativeAcquisitionCoefficient, backNumber, hittingPosition, hittingInclination, characterStatus);
        this.setCode(code);
    }

    public Character(String name, int grade, int acquisitionCoefficient, int cumulativeAcquisitionCoefficient,
                     int backNumber, HittingPosition hittingPosition, List<Float> hittingInclination, CharacterStatus characterStatus) {
        this.name = name;
        this.grade = grade;
        this.acquisitionCoefficient = acquisitionCoefficient;
        this.cumulativeAcquisitionCoefficient = cumulativeAcquisitionCoefficient;
        this.backNumber = backNumber;
        this.hittingPosition = hittingPosition;
        this.hittingInclination = hittingInclination;
        this.characterStatus = characterStatus;
    }

    @Override
    public Character clone() throws CloneNotSupportedException {
        return (Character) super.clone();
    }
}
