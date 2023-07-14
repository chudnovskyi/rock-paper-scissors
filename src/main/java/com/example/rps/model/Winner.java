package com.example.rps.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Winner {
    A("A"),
    B("B"),
    TIE("TIE");

    private final String code;
}
