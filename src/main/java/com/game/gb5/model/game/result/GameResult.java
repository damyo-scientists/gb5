package com.game.gb5.model.game.result;

import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.deck.Deck;
import com.game.gb5.model.game.Game;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameResult extends BaseEntity {
    @OneToMany
    List<InningResult> inningResults;
    @OneToOne
    Game game;
    @Column
    int deck1Score;
    @Column
    int deck2Score;
    @OneToOne
    Deck winningDeck;
}
