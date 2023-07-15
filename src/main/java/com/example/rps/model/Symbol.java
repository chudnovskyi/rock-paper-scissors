package com.example.rps.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Symbol implements EnumeratedEntityField {
    ROCK("R"),
    PAPER("P"),
    SCISSORS("S");

    private final String code;
}
