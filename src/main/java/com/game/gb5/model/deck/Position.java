package com.game.gb5.model.deck;

import lombok.Getter;

import java.util.Arrays;

public enum Position {
    NONE(-1), FIRST_BASE(0), SECOND_BASE(1), MID_FIELDER(2),
    SHORT_STOP(3), THIRD_BASE(4), BENCH1(5),
    BENCH2(6), BENCH3(7);

    @Getter
    private int positionNumber;

    public static Position findByPositionNumber(int positionNumber) {
        return Arrays.stream(Position.values()).filter(position -> position.hasPositionNumber(positionNumber)).findAny().orElse(NONE);
    }

    private boolean hasPositionNumber(int positionNumber) {
        return getPositionNumber() == positionNumber;
    }

    Position(int positionNumber) {
        this.positionNumber = positionNumber;
    }
}
