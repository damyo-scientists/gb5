package com.game.gb5.deck.controller;

import com.game.gb5.commons.AbstractControllerTest;
import com.game.gb5.deck.contrller.DeckController;
import com.game.gb5.deck.model.Deck;
import com.game.gb5.deck.service.DeckService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(DeckController.class)
public class DeckControllerTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeckService deckService;

    private final String RESOURCE_URI = "/deck";

    @Test
    public void testCreate() throws Exception {
        Deck deck = new Deck();
        deck.setId(1L);
        when(deckService.create(any(Deck.class))).thenReturn(deck);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(RESOURCE_URI)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(deck))).andReturn();

        // verify
        int status = mvcResult.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.CREATED.value(), status);

        // verify that service method was called once
        verify(deckService).create(any(Deck.class));

        Deck resultDeck = mapFromJson(mvcResult.getResponse().getContentAsString(), Deck.class);
        assertNotNull(resultDeck);
        assertEquals(1L, resultDeck.getId().longValue());
    }

    @Test
    public void testGetById() throws Exception {
        Deck deck = new Deck();
        deck.setId(1L);

        when(deckService.getById(any(Long.class))).thenReturn(deck);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE_URI + "/{id}", 1L))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        verify(deckService).getById(1L);

        Deck resultDeck = mapFromJson(mvcResult.getResponse().getContentAsString(), Deck.class);
        assertNotNull(resultDeck);
        assertEquals(1L, resultDeck.getId().longValue());
    }

    @Test
    public void testUpdate() throws Exception {
        Deck deck = new Deck();
        deck.setId(1L);

        when(deckService.getById(any(Long.class))).thenReturn(deck);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(RESOURCE_URI)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(deck))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        verify(deckService).update(any(Deck.class));
    }

    public void testDelete() throws Exception {
        Deck deck = new Deck();
        deck.setId(1L);

        when(deckService.getById(any(Long.class))).thenReturn(deck);

        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(RESOURCE_URI + "/{id}", 1L)).andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.GONE.value(), status);

        // verify that service method was called once
        verify(deckService).delete(any(Deck.class));
    }
}
