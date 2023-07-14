package com.example.rps.integration;

import com.example.rps.config.ITBase;
import com.example.rps.dto.HistoryResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.rps.enums.Url.HOME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
public class HomeControllerIT extends ITBase {

    private final MockMvc mockMvc;

    private final static HistoryResponse EMPTY_HISTORY_RESPONSE
            = new HistoryResponse(0, 0L, 0L, 0L);

    @Test
    public void getHomePageTest() throws Exception {
        mockMvc.perform(get(HOME.toString()))
                .andExpectAll(
                        status().isOk(),
                        view().name("home"),
                        model().attribute("history", EMPTY_HISTORY_RESPONSE)
                );
    }
}
