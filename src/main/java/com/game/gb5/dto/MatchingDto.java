package com.game.gb5.dto;

import com.game.gb5.model.matching.Matching;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class MatchingDto extends BaseDto<Matching> {
    private String firstDeckCode;
    private String secondDeckCode;

    public MatchingDto(String firstDeckCode, String secondDeckCode) {
        this.firstDeckCode = firstDeckCode;
        this.secondDeckCode = secondDeckCode;
    }

    @Override
    public Matching toEntity() {
        return null; // build by matchMaker
    }
}
