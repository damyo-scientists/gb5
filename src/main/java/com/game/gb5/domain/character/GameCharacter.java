package com.game.gb5.domain.character;

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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class GameCharacter extends BaseEntity {
	private static final long serialVersionUID = 4524886628421077702L;
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
	@Column
	@Enumerated(EnumType.STRING)
	private HittingPosition hittingPosition;
	@ElementCollection
	@Column
	private List<Float> hittingInclination;
	@ManyToOne(fetch = FetchType.LAZY)
	private CharacterStatus characterStatus;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "characters")
	private List<CharacterSet> characterSets;
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
	
	private CharacterStatusReport characterStatusReport;
}
