package com.game.gb5.deck.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.game.gb5.common.dto.BaseDto;
import com.game.gb5.deck.model.Deck;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ImportDeckDto extends BaseDto<Deck> {
    private Long playerId;
    private String firstBaseCode;
    private String secondBaseCode;
    private String thirdBaseCode;
    private String midFielderCode;
    private String shortStopCode;
    private String bench1Code;
    private String bench2Code;
    private String bench3Code;

    public ImportDeckDto(Long id, Long playerId, String firstBaseCode, String secondBaseCode, String thirdBaseCode,
                         String midFielderCode, String shortStopCode, String bench1Code, String bench2Code, String bench3Code) {
        this.id = id;
        this.playerId = playerId;
        this.firstBaseCode = firstBaseCode;
        this.secondBaseCode = secondBaseCode;
        this.thirdBaseCode = thirdBaseCode;
        this.midFielderCode = midFielderCode;
        this.shortStopCode = shortStopCode;
        this.bench1Code = bench1Code;
        this.bench2Code = bench2Code;
        this.bench3Code = bench3Code;
    }

    @Override
    @Deprecated
    public Deck toEntity() {
        // deckMaker 에서 별도 구현
        return null;
    }
}
