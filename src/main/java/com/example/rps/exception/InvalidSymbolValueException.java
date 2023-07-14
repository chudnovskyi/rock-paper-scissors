package com.example.rps.exception;

public class InvalidSymbolValueException extends IllegalArgumentException {

    public InvalidSymbolValueException(String message) {
        super(message);
    }
}
