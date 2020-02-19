package com.game.gb5.inventory.model.entity.voucher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.inventory.model.entity.inventory.Inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class PassList extends Voucher {
	public PassList(Inventory inventory) {
		this.inventory = inventory;
	}
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	private Inventory inventory;
	@Column
	private Integer doubleEightLeaguePass;
}
