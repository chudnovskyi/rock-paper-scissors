package com.example.rps.controller;

import com.example.rps.model.Player;
import com.example.rps.model.Symbol;
import com.example.rps.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/players")
public class PlayerController {

    private final GameService gameService;

    @GetMapping("/{id}")
    public String getPlayerPage(
            @PathVariable("id") Player player,
            Model model
    ) {
        model.addAttribute("player_id", player.toString());
        return "player";
    }

    @PostMapping("/choice")
    public String choice(
            @RequestParam("symbol") Symbol symbol,
            @RequestParam("player_id") Player player,
            Model model
    ) {
        String result = gameService.makeMove(player, symbol);
        model.addAttribute("result", result);
        model.addAttribute("player_id", player.toString());
        return "player";
    }
}
