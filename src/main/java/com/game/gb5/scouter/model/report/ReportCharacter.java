package com.game.gb5.scouter.model.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.common.model.BaseEntity;
import com.game.gb5.character.model.CharacterReportStatus;
import com.game.gb5.character.model.Character;

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
	private Character targetCharacter;
	@Transient
	private CharacterReportStatus characterReportStatus;
}
