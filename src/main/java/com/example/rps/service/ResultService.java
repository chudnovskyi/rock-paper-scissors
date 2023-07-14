package com.example.rps.service;

import com.example.rps.exception.InvalidMoveValueException;
import com.example.rps.model.Symbol;
import com.example.rps.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;
    private final MessageSourceService message;

    public String makeMove(String playerId, String move) {
        Symbol symbol;

        try {
            symbol = Symbol.valueOf(move);
        } catch (IllegalArgumentException e) {
            throw new InvalidMoveValueException(message.getProperty("symbol.invalid", move));
        }

        return ";";
    }
}
