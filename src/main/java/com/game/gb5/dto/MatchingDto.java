package com.game.gb5.dto;

import com.game.gb5.model.matching.Matching;
import com.game.gb5.service.deck.DeckService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class MatchingDto extends BaseDto<Matching> {
    private String deck1Code;
    private String deck2Code;

    public MatchingDto(String deck1Code, String deck2Code) {
        this.deck1Code = deck1Code;
        this.deck2Code = deck2Code;
    }

    @Override
    public Matching toEntity() {
        return null; // build by matchMaker
    }
}
