package com.game.gb5.simulation.system.helper;

import org.springframework.stereotype.Component;

@Component
public class HittingRandomResultCalculator {
    public HittingRandomResult checkIsHit(double finalHittingProbability) {
        double hitCheckRandomValue = Math.random();
        boolean isHit = hitCheckRandomValue < finalHittingProbability;
        return HittingRandomResult.builder().hitCheckRandomValue(hitCheckRandomValue).isHit(isHit).build();
    }
}
