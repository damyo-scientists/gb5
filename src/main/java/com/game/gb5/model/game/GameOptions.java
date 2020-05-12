package com.game.gb5.model.game;

import com.game.gb5.model.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class GameOptions extends BaseEntity {
    @Column
    private int inning = 9;
    @OneToOne
    private Game game;
}
