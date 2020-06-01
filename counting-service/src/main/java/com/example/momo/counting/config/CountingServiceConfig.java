package com.example.momo.counting.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CountingDataConfig.class)
public class CountingServiceConfig {

    @Bean
    @ConfigurationProperties(prefix = "counting.service")
    public CountingServiceProperties countingServiceProperties() {
        return new CountingServiceProperties();
    }

}
