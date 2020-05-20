package com.game.gb5.model.game.result;

import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.game.type.RunningType;
import com.game.gb5.model.game.unit.Batter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
public class RunningResult extends BaseEntity {
    @Column
    private Batter batter;

    @Enumerated
    private RunningType RunningAction;
}
