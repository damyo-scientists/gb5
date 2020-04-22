package com.game.gb5.deck.dto;

import com.game.gb5.common.dto.BaseDto;
import com.game.gb5.deck.model.Deck;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImportDeckDto extends BaseDto<Deck> {
    private Long playerId;
    private Long firstBaseId;
    private Long secondBaseId;
    private Long thirdBaseId;
    private Long midFielderId;
    private Long shortStopId;
    private Long bench1Id;
    private Long bench2Id;
    private Long bench3Id;

    public ImportDeckDto(Long playerId, Long firstBaseId, Long secondBaseId, Long thirdBaseId,
                         Long midFielderId, Long shortStopId, Long bench1Id, Long bench2Id, Long bench3Id) {
        this.playerId = playerId;
        this.firstBaseId = firstBaseId;
        this.secondBaseId = secondBaseId;
        this.thirdBaseId = thirdBaseId;
        this.midFielderId = midFielderId;
        this.shortStopId = shortStopId;
        this.bench1Id = bench1Id;
        this.bench2Id = bench2Id;
        this.bench3Id = bench3Id;
    }

    @Override
    @Deprecated
    public Deck toEntity() {
        // deckMaker 에서 별도 구현
        return null;
    }
}
