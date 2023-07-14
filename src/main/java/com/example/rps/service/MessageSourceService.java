package com.example.rps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSourceService {

    private final Environment environment;

    public String getProperty(String source) {
        return environment.getProperty(source);
    }

    public String getProperty(String source, Object... params) {
        String property = environment.getProperty(source);
        if (property == null) {
            throw new RuntimeException("Property %s not found!".formatted(source));
        }
        return String.format(property, params);
    }
}
