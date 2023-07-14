package com.example.rps.mapper;

import com.example.rps.dto.HistoryResponse;
import com.example.rps.entity.Game;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    @Mapping(target = "total", expression = "java(games.size())")
    @Mapping(target = "winsA", expression = "java(games.stream().filter(g -> \"A\".equals(g.getWinner())).count())")
    @Mapping(target = "winsB", expression = "java(games.stream().filter(g -> \"B\".equals(g.getWinner())).count())")
    @Mapping(target = "ties", expression = "java(games.stream().filter(g -> \"TIE\".equals(g.getWinner())).count())")
    HistoryResponse mapToHistoryResponse(Void q, @Context List<Game> games);
}
