package com.game.gb5.entity.scouting.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.entity.BaseEntity;
import com.game.gb5.entity.character.CharacterReportStatus;
import com.game.gb5.entity.character.GameCharacter;

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
