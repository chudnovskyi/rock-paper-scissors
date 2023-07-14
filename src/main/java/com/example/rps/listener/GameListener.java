package com.example.rps.listener;

import com.example.rps.entity.Game;
import com.example.rps.model.Symbol;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Value;

import static com.example.rps.model.Player.A;
import static com.example.rps.model.Player.B;
import static com.example.rps.model.Symbol.*;

public class GameListener {

    @Value("${result.tie}")
    private static final String TIE_RESULT = "TIE";

    @PreUpdate
    private void preUpdate(Game game) {
        Symbol symbolA = game.getSymbolA();
        Symbol symbolB = game.getSymbolB();

        if (symbolA != null && symbolB != null) { // TODO
            Symbol winnerSymbol = calculateWinner(symbolA, symbolB);
            if (winnerSymbol == null) {
                game.setWinner(TIE_RESULT);
            } else if (winnerSymbol == symbolA) {
                game.setWinner(A.toString());
            } else {
                game.setWinner(B.toString());
            }

            game.setActive(false);
        }
    }

    private Symbol calculateWinner(Symbol symbolA, Symbol symbolB) {
        if (symbolA == symbolB) {
            return null;
        }
        return switch (symbolA) {
            case ROCK -> (symbolB == SCISSORS) ? symbolA : symbolB;
            case PAPER -> (symbolB == ROCK) ? symbolA : symbolB;
            case SCISSORS -> (symbolB == PAPER) ? symbolA : symbolB;
        };
    }
}
