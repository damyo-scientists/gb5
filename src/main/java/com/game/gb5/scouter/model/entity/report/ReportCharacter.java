package com.game.gb5.scouter.model.entity.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.common.entity.BaseEntity;
import com.game.gb5.character.model.entity.CharacterReportStatus;
import com.game.gb5.character.model.entity.GameCharacter;

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
