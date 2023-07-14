package com.example.rps.mapper;

import com.example.rps.dto.HistoryResponse;
import com.example.rps.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    record GamesWrapper(List<Game> games) {
    }

    default HistoryResponse mapToHistoryResponse(List<Game> games) {
        return mapToHistoryResponse(new GamesWrapper(games));
    }

    @Mapping(target = "total", expression = "java(wrapper.games().size())")
    @Mapping(target = "winsA", expression = "java(wrapper.games().stream().filter(g -> \"A\".equals(g.getWinner())).count())")
    @Mapping(target = "winsB", expression = "java(wrapper.games().stream().filter(g -> \"B\".equals(g.getWinner())).count())")
    @Mapping(target = "ties", expression = "java(wrapper.games().stream().filter(g -> \"TIE\".equals(g.getWinner())).count())")
    HistoryResponse mapToHistoryResponse(GamesWrapper wrapper);
}
