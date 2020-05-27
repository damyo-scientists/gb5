package com.game.gb5.model.game.type;

import lombok.Getter;

import java.util.Arrays;

public enum BaseResultType {
    THREE_OUT_NO_RESULT(-1), OUTS_ON_BASE(-1), NONE(0), FIRST_BASE_RUN(1), SECOND_BASE_RUN(2), THIRD_BASE_RUN(3), HOME_IN(4);

    @Getter
    private int baseNumber;

    public static BaseResultType findByBaseNumber(int baseNumber) {
        return Arrays.stream(BaseResultType.values()).filter(running -> running.hasBaseNumber(baseNumber)).findAny().orElse(NONE);
    }

    public int getBaseNumber() {
        return this.baseNumber;
    }

    private boolean hasBaseNumber(int positionNumber) {
        if (this.baseNumber == 4 && positionNumber > 3) {
            return true;
        }
        return getBaseNumber() == positionNumber;
    }

    BaseResultType(int baseNumber) {
        this.baseNumber = baseNumber;
    }
}
