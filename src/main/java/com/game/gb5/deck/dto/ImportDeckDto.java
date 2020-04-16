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
    private String playerCode;
    private String firstBaseCode;
    private String secondBaseCode;
    private String thirdBaseCode;
    private String midFielderCode;
    private String shortStopCode;
    private String bench1Code;
    private String bench2Code;
    private String bench3Code;

    public ImportDeckDto(String playerCode, String firstBaseCode, String secondBaseCode, String thirdBaseCode, String midFielderCode, String shortStopCode, String bench1Code, String bench2Code, String bench3Code) {
        this.playerCode = playerCode;
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
