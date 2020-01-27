package com.game.gb5.domain.scouting.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.domain.BaseEntity;
import com.game.gb5.domain.character.CharacterReportStatus;
import com.game.gb5.domain.character.GameCharacter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class ReportCharacter extends BaseEntity {
	@JsonIgnore
	@ManyToOne
	private ScoutingReport scoutingReport;
	@OneToOne
	private GameCharacter targetCharacter;
	@Transient
	private CharacterReportStatus characterReportStatus;
}
