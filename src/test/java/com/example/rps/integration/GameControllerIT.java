package com.example.rps.integration;

import com.example.rps.ITBase;
import com.example.rps.dto.GameResponse;
import com.example.rps.dto.GamesHistoryResponse;
import com.example.rps.model.Player;
import com.example.rps.model.Symbol;
import com.example.rps.model.Winner;
import com.example.rps.service.MessageSourceService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.example.rps.enums.Url.*;
import static com.example.rps.model.Player.A;
import static com.example.rps.model.Player.B;
import static com.example.rps.model.Symbol.*;
import static com.example.rps.model.Winner.TIE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RequiredArgsConstructor
public class GameControllerIT extends ITBase {

    public final static GamesHistoryResponse EMPTY_HISTORY_RESPONSE
            = new GamesHistoryResponse(0, 0L, 0L, 0L);

    private final MockMvc mockMvc;
    private final MessageSourceService message;

    @Test
    public void deleteGamesTest() throws Exception {
        generateTieGame(SCISSORS);
        generateTieGame(ROCK);
        expectHistory(new GamesHistoryResponse(2, 0L, 0L, 2L));

        deleteGames();
        expectHistory(EMPTY_HISTORY_RESPONSE);
    }

    @Test
    public void getGamesTest() throws Exception {
        generateTieGame(PAPER);
        makeChoice(A, SCISSORS);
        makeChoice(B, ROCK);
        expectHistory(new GamesHistoryResponse(2, 0L, 1L, 1L));

        List<GameResponse> games = new ArrayList<>();
        games.add(new GameResponse(PAPER, PAPER, TIE));
        games.add(new GameResponse(SCISSORS, ROCK, Winner.B));
        getGamesAndExpect(games);
    }

    private void generateTieGame(Symbol symbol) throws Exception {
        makeChoice(A, symbol);
        makeChoice(B, symbol);
    }

    private void makeChoice(Player player, Symbol symbol) throws Exception {
        mockMvc.perform(post(PLAYER_CHOICE.getUrl())
                        .param("symbol", symbol.name())
                        .param("player_id", player.name()))
                .andExpectAll(
                        status().isOk(),
                        view().name("player"),
                        model().attribute("result", message.getProperty("game.choice.recorded")),
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

    private void deleteGames() throws Exception {
        mockMvc.perform(post(GAME.getUrl()))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/api/v1/home/")
                );
    }

    private void getGamesAndExpect(List<GameResponse> games) throws Exception {
        mockMvc.perform(get(GAME.getUrl()))
                .andExpectAll(
                        status().isOk(),
                        view().name("history"),
                        model().attribute("history", games)
                );
    }
}
