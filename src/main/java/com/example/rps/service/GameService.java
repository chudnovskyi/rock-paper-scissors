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
    private final MessageSourceService message;

    public String makeMove(Player player, Symbol symbol) {
        Game game = gameRepository.findByActiveTrue()
                .orElseGet(Game::new);

        if (hasPlayerMadeMove(game, player)) {
            return message.getProperty("game.choice.already-exists");
        }

        switch (player) {
            case A -> game.setSymbolA(symbol);
            case B -> game.setSymbolB(symbol);
        }
        gameRepository.saveAndFlush(game);

        return message.getProperty("game.choice.recorded");
    }

    private boolean hasPlayerMadeMove(Game game, Player player) {
        return switch (player) {
            case A -> game.getSymbolA() != null;
            case B -> game.getSymbolB() != null;
        };
    }
}
