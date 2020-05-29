package com.example.momo.echo.service;

import com.example.momo.echo.config.EchoServiceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"echo.service.serviceNumber=" + EchoServiceITest.SERVICE_NUMBER})
class EchoServiceITest {

	static final int SERVICE_NUMBER = 321;

	@Autowired
	private EchoService echoService;

	@Test
	void contextLoads() {
		assertThat(echoService.serviceNumber()).isEqualTo(SERVICE_NUMBER);
	}

	@SpringBootApplication
	@Import(EchoServiceConfig.class)
	static class TestApplication {}
}
