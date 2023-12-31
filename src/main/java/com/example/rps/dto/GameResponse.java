package com.example.rps.dto;

import com.example.rps.model.Symbol;
import com.example.rps.model.Winner;

public record GameResponse(
        Symbol symbolA,
        Symbol symbolB,
        Winner winner
) {
}
