package com.game.gb5.utils;

import lombok.Getter;

@Getter
public class Score {
    public Score(int deck1Score, int deck2Score) {
        this.deck1Score = deck1Score;
        this.deck2Score = deck2Score;
    }
    int deck1Score;
    int deck2Score;
}
