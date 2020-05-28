package com.example.momo.echo.service;

import com.example.momo.echo.config.EchoServiceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties({ EchoServiceProperties.class })
public class EchoService {

    private final EchoServiceProperties echoServiceProperties;

    public EchoService(final EchoServiceProperties echoServiceProperties) {
        this.echoServiceProperties = echoServiceProperties;
    }

    public int serviceNumber() {
        return this.echoServiceProperties.getServiceNumber();
    }

}
