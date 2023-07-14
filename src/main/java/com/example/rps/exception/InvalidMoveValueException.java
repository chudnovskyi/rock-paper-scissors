package com.example.rps.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidMoveValueException extends IllegalArgumentException {

    private String move;
    private String playerId;
}
