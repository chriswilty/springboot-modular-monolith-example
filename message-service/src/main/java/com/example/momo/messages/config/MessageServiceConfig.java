package com.example.momo.messages.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(MessageDataConfig.class)
@PropertySource("classpath:messages.properties")
/*
 Need ComponentScan to pick up all components within this library module.
 This config is then loaded by the Application module
 */
@ComponentScan(basePackages = "com.example.momo.messages")
public class MessageServiceConfig {

    @Bean
    @ConfigurationProperties(prefix = "messages.service")
    public MessageServiceProperties messageServiceProperties() {
        return new MessageServiceProperties();
    }

}
