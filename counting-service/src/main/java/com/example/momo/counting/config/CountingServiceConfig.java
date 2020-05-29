package com.example.momo.counting.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(CountingDataConfig.class)
@PropertySource("classpath:counting.properties")
public class CountingServiceConfig {

    @Bean
    @ConfigurationProperties(prefix = "counting.service")
    public CountingServiceProperties countingServiceProperties() {
        return new CountingServiceProperties();
    }

}
