package com.example.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/player1")
public class PlayerController {

    @GetMapping("/get")
    public String getHtml() {
        return "test";
    }
}
