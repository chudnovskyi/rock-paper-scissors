package com.example.rps.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class WinnerEnumConverter implements AttributeConverter<Winner, String> {

    @Override
    public String convertToDatabaseColumn(Winner winner) {
        if (winner == null) {
            return null;
        }
        return winner.getCode();
    }

    @Override
    public Winner convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Winner.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
