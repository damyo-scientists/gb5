package com.game.gb5.dto;

import com.game.gb5.model.deck.Deck;
import com.game.gb5.model.deck.DeckCharacter;
import com.game.gb5.model.deck.Position;
import com.game.gb5.model.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class DeckDto extends BaseDto<Deck> {
    private Map<Position, DeckCharacter> characters;
    private Player player;

    public DeckDto(Long id, String code, Map<Position, DeckCharacter> characters, Player player) {
        this.id = id;
        this.code = code;
        this.characters = characters;
        this.player = player;
    }

    public Deck toEntity() {
        Deck deck = new Deck(id, code, characters, player);
        if (createdDate != null) {
            deck.setCreatedDate(createdDate);
        }
        return deck;
    }
}
