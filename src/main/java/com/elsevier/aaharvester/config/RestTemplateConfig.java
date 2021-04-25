package com.elsevier.aaharvester.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {

        return builder
                .setConnectTimeout(Duration.ofMillis(24000))
                .setReadTimeout(Duration.ofMillis(24000))
                .build();
    }
}
