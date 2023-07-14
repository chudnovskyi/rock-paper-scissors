package com.example.rps.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({
        "classpath:${envTarget:error}.properties",
        "classpath:${envTarget:message}.properties"
})
public class PropertiesConfig {
}
