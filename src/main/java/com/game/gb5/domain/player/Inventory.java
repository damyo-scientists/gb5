package com.game.gb5.domain.player;

import com.game.gb5.domain.BaseEntity;
import com.game.gb5.domain.character.GameCharacter;
import com.game.gb5.domain.consumable.Consumable;
import com.game.gb5.domain.voucher.PassList;
import com.game.gb5.domain.voucher.TicketList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Inventory extends BaseEntity {
	@Column
	private int money;
	@Column
	private int star;
	@OneToOne(fetch = FetchType.LAZY)
	private TicketList ticketList;
	@OneToOne(fetch = FetchType.LAZY)
	private PassList passList;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "playerCharacterRelation")
	private List<GameCharacter> characters;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Consumable> consumables;
}
