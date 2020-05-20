package com.game.gb5.utils;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 각 확률로 나뉜 랜덤 수를 선택함
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DistributedRandomNumberPicker {

    private Map<Integer, Double> distribution;
    private double distSum;

    public DistributedRandomNumberPicker() {
        distribution = new HashMap<>();
    }

    public int getDistributedRandomNumber(List<Double> distributionList) {
        this.addNumbers(distributionList);
        return getDistributedRandomNumber();
    }

    private void addNumbers(List<Double> distributionList) {
        for (int i = 0; i < distributionList.size(); i++) {
            addNumber(i, distributionList.get(i));
        }
    }

    private void addNumber(int value, double distribution) {
        if (this.distribution.get(value) != null) {
            distSum -= this.distribution.get(value);
        }
        this.distribution.put(value, distribution);
        distSum += distribution;
    }

    private int getDistributedRandomNumber() {
        double rand = Math.random();
        double ratio = 1.0f / distSum;
        double tempDist = 0;
        for (Integer i : distribution.keySet()) {
            tempDist += distribution.get(i);
            if (rand / ratio <= tempDist) {
                return i;
            }
        }
        return 0;
    }

}
