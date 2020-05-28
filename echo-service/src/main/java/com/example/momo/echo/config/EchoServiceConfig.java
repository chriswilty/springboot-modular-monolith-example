package com.example.momo.echo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:echo.properties")
/*
 Need ComponentScan to pick up all components within this library module.
 This config is then loaded by the Application module
 */
@ComponentScan(basePackages = "com.example.momo.echo")
public class EchoServiceConfig {

    @Bean
    public EchoServiceProperties echoServiceProperties() {
        return new EchoServiceProperties();
    }

}
