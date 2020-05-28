package com.example.momo.echo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "echo.service")
public class EchoServiceProperties {
    int serviceNumber;
}
