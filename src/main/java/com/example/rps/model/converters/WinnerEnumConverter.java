package com.example.rps.model.converters;

import com.example.rps.model.Winner;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class WinnerEnumConverter extends BaseEnumConverter<Winner> {

    public WinnerEnumConverter() {
        super(Winner.class);
    }
}
