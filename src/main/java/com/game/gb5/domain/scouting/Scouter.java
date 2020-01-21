package com.game.gb5.domain.scouting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.game.gb5.domain.BaseEntity;
import com.game.gb5.domain.character.CharacterSet;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

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
	
	/**
	 * Server Only
	 */
	@Column
	private boolean isHold;
	@Column
	private LocalDateTime reportRegenTime;
}