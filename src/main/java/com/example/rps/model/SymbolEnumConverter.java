package com.example.rps.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class SymbolEnumConverter implements AttributeConverter<Symbol, String> {

    @Override
    public String convertToDatabaseColumn(Symbol symbol) {
        if (symbol == null) {
            return null;
        }
        return symbol.getCode();
    }

    @Override
    public Symbol convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Symbol.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
