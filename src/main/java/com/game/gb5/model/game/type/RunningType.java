package com.game.gb5.model.game.type;

import lombok.Getter;

import java.util.Arrays;

public enum RunningType {
    NONE(-1), OUTS(0), STOP_RUNNING(1), SAFE(2);

    @Getter
    private int runningValue;

    public static RunningType findByRunningValue(int runningValue) {
        return Arrays.stream(RunningType.values()).filter(running -> running.hasRunningValue(runningValue)).findAny().orElse(NONE);
    }

    private boolean hasRunningValue(int runningValue) {
        return getRunningValue() == runningValue;
    }

    RunningType(int runningValue) {
        this.runningValue = runningValue;
    }
}
