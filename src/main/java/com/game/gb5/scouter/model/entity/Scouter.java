package com.game.gb5.scouter.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.game.gb5.entity.BaseEntity;
import com.game.gb5.entity.character.CharacterSet;
import com.game.gb5.entity.scouting.report.ScoutingReport;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Scouter extends BaseEntity {
	@Column
	private String name;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private ScouterStatus scouterStatus;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private CharacterSet characterSet;
	@Column
	private Long seed;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ScoutingReport scoutingReport;
	
	/**
	 * Server Only
	 */
	@Column
	private boolean isHold;
	@Column
	private LocalDateTime reportRegenTime;
}