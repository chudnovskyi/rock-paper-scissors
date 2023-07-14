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
}
