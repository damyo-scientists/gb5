package com.game.gb5.domain.character;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.domain.BaseEntity;
import com.game.gb5.domain.player.Player;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class GameCharacter extends BaseEntity implements Cloneable {
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
	@ManyToOne
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
	
	@Transient
	private CharacterStatusReport characterStatusReport;
	
	@Override
	public GameCharacter clone() throws CloneNotSupportedException {
		return (GameCharacter) super.clone();
	}
}
