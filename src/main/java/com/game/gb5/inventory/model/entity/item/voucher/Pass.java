package com.game.gb5.inventory.model.entity.item.voucher;

import com.game.gb5.inventory.model.entity.item.PassType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Pass extends Voucher {
	
	@Enumerated(EnumType.STRING)
	private PassType type;
}
