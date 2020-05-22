package com.game.gb5.model.game.unit;

import com.game.gb5.model.character.CharacterStatus;
import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.deck.DeckCharacter;
import com.game.gb5.model.deck.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DeckPlayer extends BaseEntity {
    @Enumerated
    private Position position;
    @OneToOne
    private DeckCharacter deckCharacter;
    private CharacterStatus deckStatus;
    private int stamina;
    private int runningBase;
}
