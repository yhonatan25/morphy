package com.goltqup.morphy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class MorphyConfiguration {

    @Value("${capablanca.port:8080}")
    private int capablancaPort;

    @Value("${capablanca.host:localhost}")
    private String capablancaHost;

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://" + capablancaHost + ":" + capablancaPort);
    }

}