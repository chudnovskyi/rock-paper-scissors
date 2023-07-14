package com.example.rps.service;

import com.example.rps.dto.GameResponse;
import com.example.rps.dto.HistoryResponse;
import com.example.rps.entity.Game;
import com.example.rps.mapper.GameMapper;
import com.example.rps.mapper.HistoryMapper;
import com.example.rps.model.Player;
import com.example.rps.model.Symbol;
import com.example.rps.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final MessageSourceService message;
    private final HistoryMapper historyMapper;
    private final GameMapper gameMapper;

    public synchronized String makeChoice(Player player, Symbol symbol) {
        Game game = gameRepository.findByActiveTrue()
                .orElseGet(Game::new);

        if (hasPlayerMadeChoice(game, player)) {
            return message.getProperty("game.choice.already-exists");
        }

        switch (player) {
            case A -> game.setSymbolA(symbol);
            case B -> game.setSymbolB(symbol);
        }
        gameRepository.saveAndFlush(game);

        return message.getProperty("game.choice.recorded");
    }

    public HistoryResponse getHistory() { // TODO: separate service
        return Optional.of(gameRepository.findAll())
                .map(historyMapper::mapToHistoryResponse)
                .orElseThrow();
    }

    public void deleteGames() {
        gameRepository.deleteByActiveFalse();
    }

    public List<GameResponse> getGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::mapToResponse)
                .toList();
    }

    private boolean hasPlayerMadeChoice(Game game, Player player) {
        return switch (player) {
            case A -> game.getSymbolA() != null;
            case B -> game.getSymbolB() != null;
        };
    }
}
