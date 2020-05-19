package com.game.gb5.simulation.strategy;

import com.game.gb5.model.game.result.BattingResult;
import org.springframework.stereotype.Component;

@Component
public class BattingStrategyImpl implements BattingStrategy {

    @Override
    public BattingResult bat() {
        // calculateInclination
        // 타격에 따른 수비수 선택
        // 안타여부 확인 : 타자의 4개 공격 스탯(타격 파워, 타격 정확도)과 , 1차 수비수의 4개 수비 스탯(수비력, 순발력)을 비교하여, 안타 확률을 계산
        // 안타시 주루 결정
        return null;
    }
}
