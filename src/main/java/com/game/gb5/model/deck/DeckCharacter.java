package com.game.gb5.model.deck;

import com.game.gb5.model.character.Character;
import com.game.gb5.model.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DeckCharacter extends BaseEntity {
    @Builder
    public DeckCharacter(Character character) {
        this.character = character;
        this.code = character.getCode();
    }

    @OneToOne
    private Character character;
}
