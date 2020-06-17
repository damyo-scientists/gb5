package com.game.gb5.controller.matching;

import com.game.gb5.controller.common.AbstractControllerTest;
import com.game.gb5.dto.MatchingDto;
import com.game.gb5.model.game.result.GameResult;
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

    protected final String RESOURCE_URI = "/matching";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    @Test
    public void gameStartTest() throws Exception {
        MatchingDto matchgingDto = MatchingDto.builder().deck1Code("test-deck").deck2Code("test-deck").build();
        GameResult gameResult = GameResult.builder().build();
        given(deckController.gameStart(any(MatchingDto.class))).willReturn(new ResponseEntity<>(gameResult, HttpStatus.OK));

        mockMvc.perform(post(RESOURCE_URI + "/1/start")
                .contentType(contentType)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(matchgingDto)))
                .andExpect(status().isOk());

        // verify that service method was called once
        verify(deckController).gameStart(any(MatchingDto.class));
    }
}
