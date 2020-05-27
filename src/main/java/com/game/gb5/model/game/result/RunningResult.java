package com.game.gb5.model.game.result;

import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.game.type.BaseResultType;
import com.game.gb5.model.game.unit.DeckPlayer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@Builder
@Getter
@NoArgsConstructor
public class RunningResult extends BaseEntity {
    @Column
    private DeckPlayer runner;
    @Column
    private int outCount;

    @Enumerated
    private BaseResultType baseResultType;
}
