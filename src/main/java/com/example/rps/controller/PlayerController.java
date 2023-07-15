package com.example.rps.controller;

import com.example.rps.model.Player;
import com.example.rps.model.Symbol;
import com.example.rps.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

import static com.example.rps.model.Player.A;
import static com.example.rps.model.Player.B;
import static com.example.rps.model.Symbol.PAPER;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/players")
public class PlayerController {

    private final PlayerService playerService;

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
        String result = playerService.makeChoice(player, symbol);
        model.addAttribute("result", result);
        model.addAttribute("player_id", player.toString());
        return "player";
    }

    @GetMapping("/generate")
    public String generate() {
        Random random = new Random();
        Symbol[] values = Symbol.values();
        for (int i = 0; i < 100; i++) {
            playerService.makeChoice(A, PAPER);
            int rand = random.nextInt(values.length);
            playerService.makeChoice(B, values[rand]);
        }
        return "redirect:/api/v1/home/";
    }
}
