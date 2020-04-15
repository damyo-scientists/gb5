package com.game.gb5.deck.model;

import com.game.gb5.character.model.Character;
import com.game.gb5.common.model.BaseEntity;
import com.game.gb5.player.model.Player;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@Entity
public class Deck extends BaseEntity {
    @OneToMany
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Position, Character> chracters;

    @ManyToOne
    private Player player;
}
