package com.game.gb5.controller.matching;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.game.gb5.controller.common.AbstractControllerTest;
import com.game.gb5.dto.MatchingDto;
import com.game.gb5.model.game.result.GameResult;
import com.game.gb5.model.matching.Matching;
import com.game.gb5.service.deck.DeckService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Errors;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MatchingControllerTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchingController deckController;

    @Autowired
    private DeckService deckService;

    protected final String RESOURCE_URI = "/matchings";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    @Test
    public void createTest() throws Exception {
        String testDeck1 = "test-deck1";
        String testDeck2 = "test-deck2";
        MatchingDto matchingDto = MatchingDto.builder().firstDeckCode(testDeck1).secondDeckCode(testDeck2).build();

        Matching matching = Matching.builder().deck1(deckService.getByCode(testDeck1).orElseThrow()).deck2(deckService.getByCode(testDeck2).orElseThrow()).build();
        given(deckController.create(any(MatchingDto.class), any(Errors.class))).willReturn(ResponseEntity.ok(matching));

        mockMvc.perform(post(RESOURCE_URI)
                .content(mapToJson(matchingDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void gameStartTest() throws Exception {
        GameResult gameResult = GameResult.builder().build();
        given(deckController.gameStart(1L)).willReturn(new ResponseEntity<>(gameResult, HttpStatus.OK));

        mockMvc.perform(post(RESOURCE_URI + "/1/start")
                .contentType(contentType)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // verify that service method was called once
        verify(deckController).gameStart(1L);
    }
}
