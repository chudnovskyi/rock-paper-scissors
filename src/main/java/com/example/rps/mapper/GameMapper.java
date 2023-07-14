package com.example.rps.mapper;

import com.example.rps.dto.GameResponse;
import com.example.rps.entity.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameResponse mapToResponse(Game game);
}
