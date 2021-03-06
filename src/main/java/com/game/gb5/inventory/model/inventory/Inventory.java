package com.game.gb5.inventory.model.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.common.model.BaseEntity;
import com.game.gb5.inventory.model.item.CharacterPiece;
import com.game.gb5.inventory.model.item.PassType;
import com.game.gb5.inventory.model.item.TicketType;
import com.game.gb5.player.model.Player;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

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
		this.characterPieces = new HashMap<>();
		this.passes = new HashMap<>();
		this.tickets = new HashMap<>();
	}

	@Column
	private Integer money = 0;
	@Column
	private Integer star = 0;
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	private Player player;
	@ElementCollection
	@MapKeyEnumerated(EnumType.STRING)
	private Map<TicketType, Integer> tickets;
	@ElementCollection
	@MapKeyColumn(name = "pass_type")
	private Map<PassType, Integer> passes;
	@ElementCollection
	@MapKeyColumn(name = "character_piece")
	private Map<CharacterPiece, Integer> characterPieces;

	public Integer consumeTicket(TicketType ticketType) {
		Integer countBefore = tickets.get(ticketType);
		return tickets.put(ticketType, countBefore - 1);
	}
}
