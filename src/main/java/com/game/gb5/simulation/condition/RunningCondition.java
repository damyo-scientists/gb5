package com.game.gb5.simulation.condition;

public class RunningCondition {
    private int THIRD_BASE = 3;
    private int SECOND_BASE_HIT = 2;

    public boolean isAlwaysSafe(int currentBase, int hitBaseNumber) {
        return currentBase == THIRD_BASE && hitBaseNumber == SECOND_BASE_HIT;
    }
}
