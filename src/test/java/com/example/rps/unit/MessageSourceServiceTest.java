package com.example.rps.unit;

import com.example.rps.service.MessageSourceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageSourceServiceTest {

    @InjectMocks
    private MessageSourceService messageSourceService;

    @Mock
    private Environment environment;

    @Test
    public void getProperty_ReturnsPropertyValue() {
        String propertyKey = "game.choice.recorded";
        String expectedValue = "Choice recorded";
        when(environment.getProperty(propertyKey)).thenReturn(expectedValue);

        String actualValue = messageSourceService.getProperty(propertyKey);

        assertThat(actualValue).isEqualTo(expectedValue);
        verify(environment, times(1)).getProperty(propertyKey);
    }
}
