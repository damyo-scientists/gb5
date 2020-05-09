package com.game.gb5.model;

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
    public Deck(Long id, String code, Map<Position, Character> characters, Player player) {
        this.id = id;
        this.code = code;
        this.characters = characters;
        this.player = player;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Position, Character> characters = new HashMap<>();

    @ManyToOne
    private Player player;
}
