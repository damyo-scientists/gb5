package com.game.gb5.domain.scouting;

import com.game.gb5.domain.BaseEntity;
import com.game.gb5.domain.character.CharacterSet;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Scouter extends BaseEntity {
	@Column
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	private ScouterStatus scouterStatus;
	@ManyToOne(fetch = FetchType.LAZY)
	private CharacterSet characterSet;
	
	/**
	 * Server Only
	 */
	@Column
	private boolean isHold;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date reportRegenTime;
}
