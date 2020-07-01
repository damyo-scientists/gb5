package com.game.gb5.model.game.config;

import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.game.Game;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class GameOptions extends BaseEntity {
    @Column
    private int inning = 9;
    @OneToOne(cascade = CascadeType.ALL)
    private Game game;
}
