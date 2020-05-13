package com.game.gb5.model.deck;

import com.game.gb5.model.player.Player;
import com.game.gb5.model.character.Character;
import com.game.gb5.model.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Deck extends BaseEntity {
    public Deck(Long id, String code, Map<Position, DeckCharacter> deckCharacters, Player player) {
        this.id = id;
        this.code = code;
        this.deckCharacters = deckCharacters;
        this.player = player;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Position, DeckCharacter> deckCharacters = new HashMap<>();

    @ManyToOne
    private Player player;
}
