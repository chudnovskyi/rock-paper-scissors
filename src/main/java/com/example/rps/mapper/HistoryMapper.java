package com.example.rps.mapper;

import com.example.rps.dto.HistoryResponse;
import com.example.rps.entity.Game;
import com.example.rps.model.Winner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        imports = {
                Winner.class
        }
)
public interface HistoryMapper {

    record GamesWrapper(List<Game> games) {
    }

    default HistoryResponse mapToHistoryResponse(List<Game> games) {
        return mapToHistoryResponse(new GamesWrapper(games));
    }

    @Mapping(target = "total", expression = "java(wrapper.games().size())")
    @Mapping(target = "winsA", expression = "java(wrapper.games().stream().filter(g -> Winner.A.equals(g.getWinner())).count())")
    @Mapping(target = "winsB", expression = "java(wrapper.games().stream().filter(g -> Winner.B.equals(g.getWinner())).count())")
    @Mapping(target = "ties", expression = "java(wrapper.games().stream().filter(g -> Winner.TIE.equals(g.getWinner())).count())")
    HistoryResponse mapToHistoryResponse(GamesWrapper wrapper);
}
