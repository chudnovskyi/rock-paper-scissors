package com.example.rps.integration;

import com.example.rps.ITBase;
import com.example.rps.dto.GamesHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.rps.enums.Url.HOME;
import static com.example.rps.integration.GameControllerIT.EMPTY_HISTORY_RESPONSE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RequiredArgsConstructor
public class HomeControllerIT extends ITBase {

    private final MockMvc mockMvc;

    @Test
    public void getHomePageTest() throws Exception {
        getHomePageAndExpectHistory(EMPTY_HISTORY_RESPONSE);
    }

    private void getHomePageAndExpectHistory(GamesHistoryResponse history) throws Exception {
        mockMvc.perform(get(HOME.getUrl()))
                .andExpectAll(
                        status().isOk(),
                        view().name("home"),
                        model().attribute("history", history)
                );
    }
}
