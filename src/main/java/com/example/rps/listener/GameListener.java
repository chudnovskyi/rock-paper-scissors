package com.example.rps.listener;

import com.example.rps.entity.Game;
import com.example.rps.model.Symbol;
import com.example.rps.model.Winner;
import jakarta.persistence.PreUpdate;

import static com.example.rps.model.Symbol.*;
import static com.example.rps.model.Winner.*;

public class GameListener {

    @PreUpdate
    public void preUpdate(Game game) {
        Symbol symbolA = game.getSymbolA();
        Symbol symbolB = game.getSymbolB();

        if (symbolA != null && symbolB != null) {
            Winner winner = calculateWinner(symbolA, symbolB);
            game.setWinner(winner);
            game.setActive(false);
        }
    }

    private Winner calculateWinner(Symbol symbolA, Symbol symbolB) {
        if (symbolA == symbolB) {
            return TIE;
        }
        return switch (symbolA) {
            case ROCK -> (symbolB == SCISSORS) ? A : B;
            case PAPER -> (symbolB == ROCK) ? A : B;
            case SCISSORS -> (symbolB == PAPER) ? A : B;
        };
    }
}
