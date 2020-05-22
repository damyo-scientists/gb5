package com.game.gb5.model.game.result;

import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.deck.DeckCharacter;
import com.game.gb5.model.game.type.BattingType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class BattingResult extends BaseEntity {
    @OneToOne
    private DeckCharacter deckCharacter;
    @Enumerated
    private BattingType battingType;
    @OneToMany
    private List<RunningResult> runningResultList;
    @Column
    private double hitRandomResult;
}
