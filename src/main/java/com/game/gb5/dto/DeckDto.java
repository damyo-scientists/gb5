package com.game.gb5.dto;

import com.game.gb5.model.deck.Deck;
import com.game.gb5.model.deck.DeckCharacter;
import com.game.gb5.model.deck.Position;
import com.game.gb5.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class DeckDto extends BaseDto<Deck> {
    private Map<Position, DeckCharacter> characters;
    private User user;

    public DeckDto(Long id, String code, Map<Position, DeckCharacter> characters, User user) {
        this.id = id;
        this.code = code;
        this.characters = characters;
        this.user = user;
    }

    public Deck toEntity() {
        Deck deck = new Deck(id, code, characters, user);
        if (createdDate != null) {
            deck.setCreatedDate(createdDate);
        }
        return deck;
    }
}
