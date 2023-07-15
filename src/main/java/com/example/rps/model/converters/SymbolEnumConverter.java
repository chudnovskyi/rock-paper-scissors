package com.example.rps.model.converters;

import com.example.rps.model.Symbol;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SymbolEnumConverter extends BaseEnumConverter<Symbol> {

    public SymbolEnumConverter() {
        super(Symbol.class);
    }
}
