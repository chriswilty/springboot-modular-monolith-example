package com.example.momo.echo.service;

import com.example.momo.echo.config.EchoServiceProperties;
import org.springframework.stereotype.Service;

@Service
public class EchoService {

    private final EchoServiceProperties echoServiceProperties;

    public EchoService(final EchoServiceProperties echoServiceProperties) {
        this.echoServiceProperties = echoServiceProperties;
    }

    public int serviceNumber() {
        return this.echoServiceProperties.getServiceNumber();
    }

}
