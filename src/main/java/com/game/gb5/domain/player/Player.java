package com.game.gb5.domain.player;

import com.game.gb5.domain.BaseEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Player extends BaseEntity {
	@Column
	private String userId;
	@Column
	private String userName;
	@Column
	private Date reportAcquisitionResetTime;
}
