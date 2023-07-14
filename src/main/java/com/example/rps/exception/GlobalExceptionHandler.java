package com.example.rps.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidMoveValueException.class)
    public String invalidMoveValueException(Model model, InvalidMoveValueException e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}
