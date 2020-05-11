package com.game.gb5.controller;

import com.game.gb5.controller.common.AbstractControllerTest;
import com.game.gb5.dto.DeckDto;
import com.game.gb5.service.DeckService;
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
public class DeckControllerTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeckController deckController;

    @MockBean
    private DeckService deckService;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    protected final String RESOURCE_URI = "/decks";

    @Test
    public void testCreate() throws Exception {
        DeckDto deckDto = new DeckDto(null, "test-deck", null, null);
        given(deckController.create(any(DeckDto.class), any(Errors.class))).willReturn(new ResponseEntity<>(deckDto.toEntity(), HttpStatus.CREATED));

        mockMvc.perform(post(RESOURCE_URI)
                .contentType(contentType)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(deckDto)))
                .andExpect(status().isCreated());

        // verify that service method was called once
        verify(deckController).create(any(DeckDto.class), any(Errors.class));
    }

//    @Test
//    public void testGetById() throws Exception {
//        Deck deck = new Deck();
//        deck.setId(1L);
//
//        when(deckService.getById(any(Long.class))).thenReturn(deck);
//
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE_URI + "/{id}", 1L))
//                .andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
//
//        verify(deckService).getById(1L);
//
//        Deck resultDeck = mapFromJson(mvcResult.getResponse().getContentAsString(), Deck.class);
//        assertNotNull(resultDeck);
//        assertEquals(1L, resultDeck.getId().longValue());
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//        Deck deck = new Deck();
//        deck.setId(1L);
//
//        when(deckService.getById(any(Long.class))).thenReturn(deck);
//
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(RESOURCE_URI)
//                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
//                .content(mapToJson(deck))).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
//
//        verify(deckService).update(any(Deck.class));
//    }
//
//    public void testDelete() throws Exception {
//        Deck deck = new Deck();
//        deck.setId(1L);
//
//        when(deckService.getById(any(Long.class))).thenReturn(deck);
//
//        // execute
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(RESOURCE_URI + "/{id}", 1L)).andReturn();
//
//        // verify
//        int status = result.getResponse().getStatus();
//        assertEquals("Incorrect Response Status", HttpStatus.GONE.value(), status);
//
//        // verify that service method was called once
//        verify(deckService).delete(any(Deck.class));
//    }
}
