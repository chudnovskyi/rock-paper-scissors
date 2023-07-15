package com.example.rps.unit;

import com.example.rps.entity.Game;
import com.example.rps.listener.GameListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.rps.model.Symbol.*;
import static com.example.rps.model.Winner.A;
import static com.example.rps.model.Winner.TIE;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GameListenerTest {

    @InjectMocks
    private GameListener gameListener;

    @Test
    public void preUpdate_WithSymbolsSet_CalculatesWinnerAndSetsGameInactive() {
        Game game = new Game();
        game.setSymbolA(ROCK);
        game.setSymbolB(SCISSORS);

        gameListener.preUpdate(game);

        assertThat(game.getWinner()).isEqualTo(A);
        assertThat(game.isActive()).isEqualTo(false);
    }

    @Test
    public void preUpdate_WithSymbolsSet_Tie() {
        Game game = new Game();
        game.setSymbolA(PAPER);
        game.setSymbolB(PAPER);

        gameListener.preUpdate(game);

        assertThat(game.getWinner()).isEqualTo(TIE);
        assertThat(game.isActive()).isEqualTo(false);
    }

    @Test
    public void preUpdate_WithOnlyOneSymbolSet_DoesNotCalculateWinner() {
        Game game = new Game();
        game.setSymbolA(SCISSORS);

        gameListener.preUpdate(game);

        assertThat(game.getWinner()).isEqualTo(null);
        assertThat(game.isActive()).isEqualTo(true);
    }
}
