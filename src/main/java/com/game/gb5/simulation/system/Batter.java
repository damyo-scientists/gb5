package com.game.gb5.simulation.system;

import com.game.gb5.model.deck.DeckCharacter;
import com.game.gb5.model.deck.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Batter {
    private Position position;
    private DeckCharacter deckCharacter;
    private int stamina;
}
