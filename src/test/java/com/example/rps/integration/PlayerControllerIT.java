package com.example.rps.integration;

import com.example.rps.ITBase;
import com.example.rps.dto.GamesHistoryResponse;
import com.example.rps.model.Player;
import com.example.rps.model.Symbol;
import com.example.rps.service.MessageSourceService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.rps.enums.Url.*;
import static com.example.rps.model.Player.A;
import static com.example.rps.model.Player.B;
import static com.example.rps.model.Symbol.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RequiredArgsConstructor
public class PlayerControllerIT extends ITBase {

    private final MockMvc mockMvc;
    private final MessageSourceService message;

    @Test
    public void getPlayerPageTest() throws Exception {
        getPlayerPageExpectBadRequest("dummy");
        getPlayerPage(A);
        getPlayerPage(B);
    }

    @Test
    public void makeChoiceTest() throws Exception {
        makeChoice(A, ROCK, message.getProperty("game.choice.recorded"));
        makeChoice(A, PAPER, message.getProperty("game.choice.already-exists"));
        makeChoice(B, SCISSORS, message.getProperty("game.choice.recorded"));
        expectHistory(new GamesHistoryResponse(1, 1L, 0L, 0L));

        makeChoice(A, PAPER, message.getProperty("game.choice.recorded"));
        expectHistory(new GamesHistoryResponse(2, 1L, 0L, 0L));
    }

    @Test
    public void generateGamesTest() throws Exception {
        generateGames();
    }

    private void getPlayerPage(Player player) throws Exception {
        mockMvc.perform(get(PLAYER.getUrl() + player))
                .andExpectAll(
                        status().isOk(),
                        view().name("player"),
                        model().attribute("player_id", player.name())
                );
    }

    private void getPlayerPageExpectBadRequest(String player) throws Exception {
        mockMvc.perform(get(PLAYER.getUrl() + player))
                .andExpectAll(
                        status().isBadRequest()
                );
    }

    private void makeChoice(Player player, Symbol symbol, String result) throws Exception {
        mockMvc.perform(post(PLAYER_CHOICE.getUrl())
                        .param("symbol", symbol.name())
                        .param("player_id", player.name()))
                .andExpectAll(
                        status().isOk(),
                        view().name("player"),
                        model().attribute("result", result),
                        model().attribute("player_id", player.name())
                );
    }

    private void expectHistory(GamesHistoryResponse history) throws Exception {
        mockMvc.perform(get(HOME.getUrl()))
                .andExpectAll(
                        status().isOk(),
                        view().name("home"),
                        model().attribute("history", history)
                );
    }

    private void generateGames() throws Exception {
        mockMvc.perform(get(PLAYER_GENERATE.getUrl()))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/api/v1/home/")
                );
    }
}
