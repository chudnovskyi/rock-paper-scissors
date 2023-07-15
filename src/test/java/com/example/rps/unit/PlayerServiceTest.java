package com.example.rps.unit;

import com.example.rps.entity.Game;
import com.example.rps.repository.GameRepository;
import com.example.rps.service.MessageSourceService;
import com.example.rps.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

import static com.example.rps.model.Player.A;
import static com.example.rps.model.Player.B;
import static com.example.rps.model.Symbol.PAPER;
import static com.example.rps.model.Symbol.ROCK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private GameRepository gameRepository;
    @Mock
    private MessageSourceService message;

    @Value("game.choice.recorded")
    private String recorded;

    @Value("game.choice.already-exists")
    private String alreadyExists;

    @Test
    public void makeChoice_WithValidChoice_ReturnsSuccessMessage() {
        Game game = new Game();
        when(gameRepository.findByActiveTrue()).thenReturn(Optional.of(game));
        when(message.getProperty("game.choice.recorded")).thenReturn(recorded);

        String result = playerService.makeChoice(A, ROCK);

        assertThat(result).isEqualTo(recorded);
        verify(gameRepository, times(1)).findByActiveTrue();
        verify(gameRepository, times(1)).saveAndFlush(game);
        verify(message, times(1)).getProperty("game.choice.recorded");
    }

    @Test
    public void makeChoice_WithExistingChoice_ReturnsErrorMessage() {
        Game game = new Game();
        game.setSymbolB(PAPER);
        when(gameRepository.findByActiveTrue()).thenReturn(Optional.of(game));
        when(message.getProperty("game.choice.already-exists")).thenReturn(alreadyExists);

        String result = playerService.makeChoice(B, ROCK);

        assertThat(result).isEqualTo(alreadyExists);
        verify(gameRepository, times(1)).findByActiveTrue();
        verify(gameRepository, times(0)).saveAndFlush(game);
        verify(message, times(1)).getProperty("game.choice.already-exists");
    }
}
