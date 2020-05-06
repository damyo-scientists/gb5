package com.game.gb5.match.service;

import com.game.gb5.deck.model.Position;
import com.game.gb5.deck.service.DeckServiceTest;
import com.game.gb5.match.dto.MatchDto;
import com.game.gb5.match.model.Match;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchServiceTest {

    @Autowired
    private MatchService matchService;

    @Test
    public void testImportData() throws ExecutionException, InterruptedException {
        DeckServiceTest deckServiceTest = new DeckServiceTest();
        deckServiceTest.testImportData();

        List<MatchDto> matchDtoList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            MatchDto matchDto = new MatchDto("test-deck", "test-deck");
            matchDto.setCode("match-" + i);
            matchDtoList.add(matchDto);
        }

        matchService.importData(matchDtoList).get();

        Optional<Match> match1 = matchService.getByCode("match-1");
        Assert.assertTrue(match1.isPresent());

        Optional<Match> match2 = matchService.getByCode("match-2");
        Assert.assertTrue(match2.isPresent());

        Assert.assertEquals("test character 1", match1.get().getDeck1().getCharacters().get(Position.FIRST_BASE).getName());
        Assert.assertEquals("test character 1", match2.get().getDeck1().getCharacters().get(Position.FIRST_BASE).getName());
    }
}
