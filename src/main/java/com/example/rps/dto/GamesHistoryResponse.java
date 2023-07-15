package com.example.rps.dto;

public record GamesHistoryResponse(
        Integer total,
        Long winsA,
        Long winsB,
        Long ties
) {
}
