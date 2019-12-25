package com.game.gb5.domain.voucher;

import com.game.gb5.domain.player.Player;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class PassList extends Voucher {
	@Column
	int doubleEightLeaguePass;
}
