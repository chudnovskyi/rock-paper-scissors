package com.example.rps.service;

import com.example.rps.dto.GameResponse;
import com.example.rps.dto.GamesHistoryResponse;
import com.example.rps.mapper.GameMapper;
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
    private final GameMapper historyMapper;
    private final GameMapper gameMapper;

    public GamesHistoryResponse getGamesHistory() {
        return Optional.of(gameRepository.findAll())
                .map(historyMapper::mapToHistoryResponse)
                .orElse(GamesHistoryResponse.builder().build());
    }

    public List<GameResponse> getGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::mapToResponse)
                .toList();
    }

    public void deleteGames() {
        gameRepository.deleteByActiveFalse();
    }
}
