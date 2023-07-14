package com.example.rps.dto;

public record HistoryResponse(
        Integer total,
        Long winsA,
        Long winsB,
        Long ties
) {
}
