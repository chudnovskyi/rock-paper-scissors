package com.example.rps.dto;

import com.example.rps.model.Symbol;

public record GameResponse(
        Long id,
        Symbol symbolA,
        Symbol symbolB,
        String winner // TODO: winner enum
) {
}
