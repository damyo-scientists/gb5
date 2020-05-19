package com.game.gb5.model.game.result;

import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.deck.DeckCharacter;
import com.game.gb5.model.game.result.action.BattingAction;
import com.game.gb5.model.game.result.action.RunningAction;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Getter
@Setter
public class BattingResult extends BaseEntity {
    @OneToOne
    private DeckCharacter deckCharacter;
    @Enumerated
    private BattingAction battingAction;
    @OneToMany
    private List<RunningAction> runningActionList;
}
