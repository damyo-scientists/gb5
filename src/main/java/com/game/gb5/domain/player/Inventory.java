package com.game.gb5.domain.player;

import com.game.gb5.domain.BaseEntity;
import com.game.gb5.domain.consumable.Consumable;
import com.game.gb5.domain.voucher.PassList;
import com.game.gb5.domain.voucher.TicketList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "inventory")
	private List<Consumable> consumables;
}