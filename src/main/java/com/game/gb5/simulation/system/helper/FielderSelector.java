package com.game.gb5.simulation.system.helper;

import com.game.gb5.model.deck.Position;
import com.game.gb5.model.game.unit.DeckPlayer;
import com.game.gb5.model.game.unit.Squad;
import com.game.gb5.utils.DistributedRandomNumberPicker;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FielderSelector {
    private DistributedRandomNumberPicker distributedRandomNumberPicker;

    public void setDistributedRandomNumberPicker(DistributedRandomNumberPicker
                                                         distributedRandomNumberPicker) {
        this.distributedRandomNumberPicker = distributedRandomNumberPicker;
    }

    /**
     * 타격에 따른 수비수 선택
     *
     * @param hittingInclination
     * @param fieldSquad
     * @return
     */
    public DeckPlayer selectFielder(List<Double> hittingInclination, Squad fieldSquad) {
        int randomIndex = distributedRandomNumberPicker.getDistributedRandomNumber(hittingInclination);
        Position selectedPosition = Position.findByPositionNumber(randomIndex);
        return fieldSquad.getLineup().stream().filter(player -> player.getPosition().getPositionNumber() == selectedPosition.getPositionNumber()).findFirst().orElseThrow();
    }
}
