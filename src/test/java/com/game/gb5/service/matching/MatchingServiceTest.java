package com.game.gb5.service.matching;

import com.game.gb5.dto.CharacterDto;
import com.game.gb5.dto.ImportDeckDto;
import com.game.gb5.dto.MatchingDto;
import com.game.gb5.model.character.CharacterStatus;
import com.game.gb5.model.character.HittingPosition;
import com.game.gb5.model.deck.Position;
import com.game.gb5.model.game.Game;
import com.game.gb5.model.matching.Matching;
import com.game.gb5.model.player.Player;
import com.game.gb5.service.character.CharacterService;
import com.game.gb5.service.deck.DeckService;
import com.game.gb5.service.game.GameService;
import com.game.gb5.service.player.PlayerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchingServiceTest {
    @Autowired
    private MatchingService matchingService;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private DeckService deckService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Test
    @Transactional
    public void testImportData() throws ExecutionException, InterruptedException {
        this.makeDeck();
        List<MatchingDto> matchingDtoList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            MatchingDto matchingDto = new MatchingDto("test-deck", "test-deck");
            matchingDto.setCode("match-" + i);
            matchingDtoList.add(matchingDto);
        }

        matchingService.importData(matchingDtoList).get();

        Optional<Matching> match1 = matchingService.getByCode("match-1");
        Assert.assertTrue(match1.isPresent());

        Optional<Matching> match2 = matchingService.getByCode("match-2");
        Assert.assertTrue(match2.isPresent());

        Assert.assertEquals("test character 1", match1.get().getDeck1().getDeckCharacters().get(Position.FIRST_BASE).getCharacter().getName());
        Assert.assertEquals("test character 1", match2.get().getDeck1().getDeckCharacters().get(Position.FIRST_BASE).getCharacter().getName());
    }

    @Test
    @Transactional
    public void testStartGame() throws ExecutionException, InterruptedException {
        //this.makeDeck();
        MatchingDto matchingDto = new MatchingDto("test-deck", "test-deck");
        Matching matching = matchingService.create(matchingDto);
        Assert.assertFalse(matching.isOpened());

        Game game = gameService.create(matching);
        matchingService.starGame(matching, game);

        Assert.assertTrue(matching.isOpened());
    }

    private void makeDeck() throws ExecutionException, InterruptedException {
        Player player = playerService.create("test_player", "test player");

        List<CharacterDto> characterDtoList = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            CharacterStatus characterStatus = new CharacterStatus(31, 68, 87, 85,
                    90, 52, 57, 53, 44, 44, 72);

            CharacterDto characterDto = new CharacterDto(0L, "test-code" + i, "test character " + i,
                    2, 0, 0, i, HittingPosition.LEFT, new ArrayList<>(), characterStatus);
            characterDtoList.add(characterDto);
        }

        characterService.importData(characterDtoList).get();

        ImportDeckDto importDeckDto = new ImportDeckDto(null, player.getId(), "test-code1", "test-code2", "test-code3", "test-code4", "test-code5", "test-code6", "test-code7", "test-code8");
        String deckCode = "test-deck";
        importDeckDto.setCode(deckCode);
        List<ImportDeckDto> deckDtos = new ArrayList<>();
        deckDtos.add(importDeckDto);
        deckService.importData(deckDtos).get();
    }
}
