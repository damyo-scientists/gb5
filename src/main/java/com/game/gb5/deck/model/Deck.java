package com.game.gb5.deck.model;

import com.game.gb5.character.model.Character;
import com.game.gb5.common.model.BaseEntity;
import com.game.gb5.player.model.Player;
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
    public Deck(Long id, String code, Map<Position, Character> chracters, Player player) {
        this.id = id;
        this.code = code;
        this.chracters = chracters;
        this.player = player;
    }

    @OneToMany
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Position, Character> chracters = new HashMap<>();

    @ManyToOne
    private Player player;
}
