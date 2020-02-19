package com.game.gb5.inventory.model.entity.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.common.entity.BaseEntity;
import com.game.gb5.inventory.model.entity.voucher.PassList;
import com.game.gb5.inventory.model.entity.voucher.TicketList;
import com.game.gb5.inventory.model.entity.consumable.Consumable;
import com.game.gb5.player.model.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Inventory extends BaseEntity {
	public Inventory(Player player) {
		this.player = player;
		this.consumables = new ArrayList<>();
		this.passList = new PassList(this);
		this.ticketList = new TicketList(this);
	}
	
	@Column
	private Integer money = 0;
	@Column
	private Integer star = 0;
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	private Player player;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "inventory", cascade = CascadeType.ALL)
	private TicketList ticketList;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "inventory", cascade = CascadeType.ALL)
	private PassList passList;
	@OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
	private List<Consumable> consumables;
}
