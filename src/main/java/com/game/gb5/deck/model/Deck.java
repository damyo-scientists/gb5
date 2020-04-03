package com.game.gb5.deck.model;

import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
public class Deck extends BaseEntity {
    @OneToMany
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Position, GameCharacter> startingMemberByPosition;

    @OneToMany
    private List<GameCharacter> replacementMemberList;
}
