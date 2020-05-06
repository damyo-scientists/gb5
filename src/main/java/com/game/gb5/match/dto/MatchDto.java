package com.game.gb5.match.dto;

import com.game.gb5.common.dto.BaseDto;
import com.game.gb5.match.model.Match;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchDto extends BaseDto<Match> {
    private String deck1Code;
    private String deck2Code;

    public MatchDto(String deck1Code, String deck2Code) {
        this.deck1Code = deck1Code;
        this.deck2Code = deck2Code;
    }

    @Override
    public Match toEntity() {
        return null;
    }
}
