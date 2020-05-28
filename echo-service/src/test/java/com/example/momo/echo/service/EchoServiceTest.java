package com.example.momo.echo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"echo.service.serviceNumber=" + EchoServiceTest.SERVICE_NUMBER})
class EchoServiceTest {

	static final int SERVICE_NUMBER = 321;

	@Autowired
	private EchoService echoService;

	@Test
	void contextLoads() {
		assertThat(echoService.serviceNumber()).isEqualTo(SERVICE_NUMBER);
	}

	@SpringBootApplication
	static class TestApplication {}
}
