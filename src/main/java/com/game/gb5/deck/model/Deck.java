package com.game.gb5.deck.model;

import com.game.gb5.character.model.GameCharacter;

import java.util.List;
import java.util.Map;

public class Deck {
    private Map<Position, GameCharacter> memberByPosition;
    private List<GameCharacter> replacementMemberList;
}
