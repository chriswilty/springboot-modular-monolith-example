package com.example.momo.echo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:echo.properties")
public class EchoServiceConfig {

    @Bean
    @ConfigurationProperties(prefix = "echo.service")
    public EchoServiceProperties echoServiceProperties() {
        return new EchoServiceProperties();
    }

}
