package com.game.gb5.dto;

import com.game.gb5.model.Matching;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchingDto extends BaseDto<Matching> {
    private String deck1Code;
    private String deck2Code;

    public MatchingDto(String deck1Code, String deck2Code) {
        this.deck1Code = deck1Code;
        this.deck2Code = deck2Code;
    }

    @Override
    public Matching toEntity() {
        return null;
    }
}
