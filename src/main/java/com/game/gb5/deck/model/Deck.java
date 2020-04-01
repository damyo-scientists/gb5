package com.game.gb5.deck.model;

import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
public class Deck extends BaseEntity {
    @OneToMany
    private Map<Position, GameCharacter> startingMemberByPosition;

    @OneToMany
    private List<GameCharacter> replacementMemberList;
}
