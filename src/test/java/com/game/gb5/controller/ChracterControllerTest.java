package com.game.gb5.controller;

import com.game.gb5.controller.common.AbstractControllerTest;
import com.game.gb5.dto.CharacterDto;
import com.game.gb5.model.Character;
import com.game.gb5.model.CharacterStatus;
import com.game.gb5.model.HittingPosition;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChracterControllerTest extends AbstractControllerTest {
    private static String RESOURCE_URI = "/characters";

    @MockBean
    private CharacterController characterController;

    @Autowired
    private MockMvc mockMvc;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    @Test
    public void testGet() throws Exception {
        CharacterStatus characterStatus = new CharacterStatus(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        given(characterController.getById(1)).willReturn(new ResponseEntity<>(new Character("리눅스", 1, 2,
                3, 4, HittingPosition.RIGHT, null, characterStatus), HttpStatus.OK));
        mockMvc.perform(get(RESOURCE_URI + "/{character_id}", 1)).andExpect(status().isOk())
                .andExpect(jsonPath("$['name']", containsString("리눅스"))).andDo(print());
    }

    @Test
    public void testCreate() throws Exception {
        CharacterStatus characterStatus = new CharacterStatus(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        CharacterDto characterDto = new CharacterDto(0L, "test-code", "하나", 1, 1, 1, 1, HittingPosition.RIGHT, null, characterStatus);
        given(characterController.create(any(CharacterDto.class), any(Errors.class))).willReturn(new ResponseEntity<>(new Character("하나", 1, 0,
                0, 1, HittingPosition.RIGHT, null, characterStatus), HttpStatus.CREATED));

        mockMvc.perform(post(RESOURCE_URI)
                .contentType(contentType)
                .content(mapToJson(characterDto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$['name']", containsString("하나"))).andDo(print());
    }
}
