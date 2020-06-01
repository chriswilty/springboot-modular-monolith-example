package com.example.momo.messages.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MessageDataConfig.class)
public class MessageServiceConfig {

    @Bean
    @ConfigurationProperties(prefix = "messages.service")
    public MessageServiceProperties messageServiceProperties() {
        return new MessageServiceProperties();
    }

}
