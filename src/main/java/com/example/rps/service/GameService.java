package com.example.rps.service;

import com.example.rps.entity.Game;
import com.example.rps.model.Player;
import com.example.rps.model.Symbol;
import com.example.rps.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public String makeMove(Player player, Symbol symbol) {
        Game game = gameRepository.findByActiveTrue()
                .orElseGet(Game::new);

        if (hasPlayerMadeMove(game, player)) {
            return "You have already made your choice!";
        }

        switch (player) {
            case A -> game.setPlayerASymbol(symbol);
            case B -> game.setPlayerBSymbol(symbol);
        }
        gameRepository.saveAndFlush(game);

        return "Your answer is recorded, wait for the answer of the second player.";
    }

    private boolean hasPlayerMadeMove(Game game, Player player) {
        return switch (player) {
            case A -> game.getPlayerASymbol() != null;
            case B -> game.getPlayerBSymbol() != null;
        };
    }
}
