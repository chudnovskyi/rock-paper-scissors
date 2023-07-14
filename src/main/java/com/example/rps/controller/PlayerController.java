package com.example.rps.controller;

import com.example.rps.model.Symbol;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/players")
public class PlayerController {

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
        Symbol symbol;
        try {
            symbol = Symbol.valueOf(move);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Invalid attribute");
            return gameGamePage(playerId, model);
        }

        // TODO: call to service

        return gameGamePage(playerId, model);
    }
}
