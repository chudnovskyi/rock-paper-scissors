package com.example.rps.controller;

import com.example.rps.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/players")
public class PlayerController {

    private final ResultService resultService;

    @GetMapping("/{id}")
    public String gameGamePage(
            @PathVariable("id") String playerId,
            Model model
    ) {
        model.addAttribute("player_id", playerId);
        return "game";
    }

    @PostMapping("/move")
    public String move(
            @RequestParam("move") String move,
            @RequestParam("player_id") String playerId,
            Model model
    ) {
        String result = resultService.makeMove(playerId, move);
        model.addAttribute("result", result);
        return gameGamePage(playerId, model);
    }
}
