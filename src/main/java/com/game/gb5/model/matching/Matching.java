package com.game.gb5.model.matching;

import com.game.gb5.dto.MatchingDto;
import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.deck.Deck;
import com.game.gb5.model.game.Game;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Matching extends BaseEntity {
    @OneToOne
    private Deck deck1;
    @OneToOne
    private Deck deck2;
    @Column(nullable = false)
    private boolean isOpened;
    @OneToOne(mappedBy = "matching", cascade = CascadeType.ALL)
    private Game game;

    public void updateFromDto(MatchingDto matchingDto) {
    }
}
