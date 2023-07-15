package com.example.rps.dto;

import lombok.Builder;

@Builder
public record GamesHistoryResponse(
        Integer total,
        Long winsA,
        Long winsB,
        Long ties
) {
}
