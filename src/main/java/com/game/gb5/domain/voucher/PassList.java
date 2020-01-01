package com.game.gb5.domain.voucher;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PassList extends Voucher {
	@Column
	int doubleEightLeaguePass;
}
