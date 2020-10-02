package com.game.gb5.simulation.strategy;

import com.game.gb5.model.game.result.HitResult;
import com.game.gb5.model.game.unit.DeckPlayer;
import com.game.gb5.model.game.unit.Squad;
import com.game.gb5.simulation.system.helper.FielderSelector;
import com.game.gb5.simulation.system.helper.HittingRandomResult;
import com.game.gb5.simulation.system.helper.HittingRandomResultCalculator;
import com.game.gb5.simulation.system.helper.HittingRateResultCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BattingStrategy {
    private FielderSelector fielderSelector;
    private HittingRateResultCalculator hittingRateResultCalculator;
    private HittingRandomResultCalculator hittingRandomResultCalculator;

    @Autowired
    public void setHittingRandomResultCalculator(HittingRandomResultCalculator hittingRandomResultCalculator) {
        this.hittingRandomResultCalculator = hittingRandomResultCalculator;
    }

    @Autowired
    public void setHittingRateResultCalculator(HittingRateResultCalculator hittingRateResultCalculator) {
        this.hittingRateResultCalculator = hittingRateResultCalculator;
    }

    @Autowired
    public void setFielderSelector(FielderSelector fielderSelector) {
        this.fielderSelector = fielderSelector;
    }

    /**
     * @param batter
     * @param fieldSquad
     * @return
     */
    public HitResult hit(DeckPlayer batter, Squad fieldSquad) {
        // calculateInclination
        List<Double> hittingInclination = batter.getDeckCharacter().getCharacter().getHittingInclination();
        DeckPlayer selectedFielder = fielderSelector.selectFielder(hittingInclination, fieldSquad);
        // 안타여부 확인 : 타자의 4개 공격 스탯(타격 파워, 타격 정확도)과 , 1차 수비수의 4개 수비 스탯(수비력, 순발력)을 비교하여, 안타 확률을 계산
        double finalHittingProbability = hittingRateResultCalculator.calculateFinalHittingProbability(batter, selectedFielder);
        HittingRandomResult hittingRandomResult = hittingRandomResultCalculator.checkIsHit(finalHittingProbability);
        // 안타시 주루 결정
        return HitResult.builder().finalHittingProbability(finalHittingProbability)
                .hitCheckRandomValue(hittingRandomResult.getHitCheckRandomValue()).isHit(hittingRandomResult.isHit()).build();
    }
}
