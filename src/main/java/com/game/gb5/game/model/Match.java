package com.game.gb5.game.model;

import com.game.gb5.common.model.BaseEntity;
import com.game.gb5.deck.model.Deck;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Match extends BaseEntity {
    @OneToOne
    private Deck deck1;
    @OneToOne
    private Deck deck2;
}
