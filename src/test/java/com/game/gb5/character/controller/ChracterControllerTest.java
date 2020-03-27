package com.game.gb5.character.controller;

import com.game.gb5.character.model.CharacterStatus;
import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.character.model.HittingPosition;
import com.game.gb5.commons.AbstractControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChracterControllerTest extends AbstractControllerTest {
    private static String RESOURCE_URI = "/characters";

    @MockBean
    private CharacterController characterController;
    private MockMvc mockMvc;

    @Before
    public void setUpt() {
        mockMvc = MockMvcBuilders.standaloneSetup(characterController).build();
    }

    @Test
    public void testGetCharacter() throws Exception {
        CharacterStatus characterStatus = new CharacterStatus(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        given(characterController.getById(1)).willReturn(new ResponseEntity<>(new GameCharacter("하나", 1, 2,
                3, 4, HittingPosition.RIGHT, null, characterStatus), HttpStatus.OK));
        mockMvc.perform(get(RESOURCE_URI + "/{character_id}", 1)).andExpect(status().isOk())
                .andExpect(jsonPath("$['name']", containsString("하나"))).andDo(print());
    }
}
