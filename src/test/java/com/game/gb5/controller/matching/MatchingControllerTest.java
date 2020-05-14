package com.game.gb5.controller.matching;

import com.game.gb5.service.matching.MatchingService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MatchingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchingController deckController;

    @MockBean
    private MatchingService deckService;

}
