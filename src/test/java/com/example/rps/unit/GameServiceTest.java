package com.example.rps.unit;

import com.example.rps.dto.GameResponse;
import com.example.rps.dto.GamesHistoryResponse;
import com.example.rps.mapper.GameMapper;
import com.example.rps.repository.GameRepository;
import com.example.rps.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;
    @Mock
    private GameMapper gameMapper;

    @Test
    void getGamesHistory_ReturnsGamesHistoryResponse() {
        when(gameRepository.findAll()).thenReturn(Collections.emptyList());

        GamesHistoryResponse actualResponse = gameService.getGamesHistory();

        assertThat(actualResponse).isEqualTo(GamesHistoryResponse.builder().build());
    }

    @Test
    void getGames_ReturnsListGameResponse() {
        when(gameRepository.findAll()).thenReturn(Collections.emptyList());
        List<GameResponse> expectedResponse = Collections.emptyList();
        List<GameResponse> actualResponse = gameService.getGames();

        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(gameRepository, times(1)).findAll();
        verify(gameMapper, times(0)).mapToResponse(any());
    }

    @Test
    void deleteGames_CallsGameRepositoryDeleteByActiveFalse() {
        gameService.deleteGames();

        verify(gameRepository, times(1)).deleteByActiveFalse();
    }
}
