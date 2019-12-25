package com.game.gb5.domain.player;

import com.game.gb5.domain.BaseEntity;
import com.game.gb5.domain.character.GameCharacter;
import com.game.gb5.domain.voucher.PassList;
import com.game.gb5.domain.voucher.TicketList;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

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
	private int money;
	@Column
	private int star;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "playerCharacterRelation")
	private List<GameCharacter> characters;
	@Column
	private String userName;
	@Column
	private Date reportAcquisitionResetTime;
	@OneToOne(fetch = FetchType.LAZY)
	private TicketList ticketList;
	@OneToOne(fetch = FetchType.LAZY)
	private PassList passList;
}
