package com.example.rps.controller;

import com.example.rps.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/history")
public class HistoryController {

    private final GameService gameService;

    @PostMapping("/")
    public String clear() {
        gameService.deleteGames();
        return "redirect:/api/v1/home/";
    }
}
