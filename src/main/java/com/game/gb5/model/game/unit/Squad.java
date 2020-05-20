package com.game.gb5.model.game.unit;

import com.game.gb5.model.game.type.SquadType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Queue;

@Getter
@Setter
@Builder
public class Squad {
    private SquadType squadType;
    private Queue<Batter> lineup;
    private List<Batter> bench;
}
