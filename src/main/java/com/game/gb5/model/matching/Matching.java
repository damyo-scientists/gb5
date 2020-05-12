package com.game.gb5.model.matching;

import com.game.gb5.dto.MatchingDto;
import com.game.gb5.model.Deck;
import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.game.Game;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Matching extends BaseEntity {
    @OneToOne
    private Deck deck1;
    @OneToOne
    private Deck deck2;
    @Column
    private boolean isOpened;
    @OneToOne(mappedBy = "matching")
    private Game game;

    public void updateFromDto(MatchingDto matchingDto) {
    }
}
