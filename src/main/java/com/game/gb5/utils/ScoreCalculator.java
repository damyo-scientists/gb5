package com.game.gb5.utils;

import com.game.gb5.model.game.result.BattingResult;
import com.game.gb5.model.game.result.InningResult;
import com.game.gb5.model.game.type.InningType;

import java.util.List;

public class ScoreCalculator {
    public static Score calcScore(List<InningResult> inningResults) {
        int deck1Count = inningResults.parallelStream().filter(inningResult -> inningResult.getInningType() == InningType.FIRST)
                .mapToInt(inningResult -> inningResult.getBattingResultList().parallelStream().mapToInt(BattingResult::getScoringCount).sum()).sum();
        int deck2Count = inningResults.parallelStream().filter(inningResult -> inningResult.getInningType() == InningType.LAST)
                .mapToInt(inningResult -> inningResult.getBattingResultList().parallelStream().mapToInt(BattingResult::getScoringCount).sum()).sum();
        return new Score(deck1Count, deck2Count);
    }
}
