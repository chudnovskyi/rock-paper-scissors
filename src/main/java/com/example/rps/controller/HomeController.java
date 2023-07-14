package com.example.rps.controller;

import com.example.rps.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/home")
public class HomeController {

    private final GameService gameService;

    @GetMapping("/")
    public String getHomePage(
            Model model
    ) {
        model.addAttribute("history", gameService.getHistory());
        return "home";
    }
}
