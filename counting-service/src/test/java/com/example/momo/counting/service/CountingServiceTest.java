package com.example.momo.counting.service;

import com.example.momo.counting.config.CountingServiceProperties;
import com.example.momo.counting.jpa.repository.CountingRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CountingServiceTest {
	// TODO Make these integration tests, as a proof-of-concept for DB integration tests.
	// Need setup and teardown to reset db between tests.

	static final int SERVICE_NUMBER = 666;

	@Mock
	private CountingRepository countingRepository;

	private CountingService countingService;

	@Test
	void readsServiceNumber() {
		countingService = new CountingService(
				CountingServiceProperties.builder().serviceNumber(SERVICE_NUMBER).build(),
				null
		);
		assertThat(countingService.serviceNumber()).isEqualTo(SERVICE_NUMBER);
	}

	@Test @Disabled("Needs implementation!")
	void addsOne() {

	}

	@Test @Disabled("Needs implementation!")
	void subtractsOne() {

	}
}
