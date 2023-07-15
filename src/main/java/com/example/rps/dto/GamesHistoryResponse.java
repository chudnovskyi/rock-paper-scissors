package com.example.rps.dto;

import lombok.Builder;

@Builder
public record GamesHistoryResponse(
        int total,
        long winsA,
        long winsB,
        long ties
) {
}
