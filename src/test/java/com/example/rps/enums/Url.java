package com.example.rps.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Url {
    GAME("/api/v1/games/"),
    HOME("/api/v1/home/"),
    PLAYER("/api/v1/players/"),
    PLAYER_CHOICE("/api/v1/players/choice"),
    PLAYER_GENERATE("/api/v1/players/generate");

    private final String url;
}
