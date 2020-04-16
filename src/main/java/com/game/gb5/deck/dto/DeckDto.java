package com.game.gb5.deck.dto;

import com.game.gb5.character.model.Character;
import com.game.gb5.common.dto.BaseDto;
import com.game.gb5.deck.model.Deck;
import com.game.gb5.deck.model.Position;
import com.game.gb5.player.model.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class DeckDto extends BaseDto<Deck> {
    private Map<Position, Character> characters;
    private Player player;

    public DeckDto(Long id, String code, Map<Position, Character> characters, Player player) {
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
