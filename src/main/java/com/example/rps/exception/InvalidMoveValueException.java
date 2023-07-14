package com.example.rps.exception;

public class InvalidMoveValueException extends IllegalArgumentException {

    public InvalidMoveValueException(String message) {
        super(message);
    }
}
