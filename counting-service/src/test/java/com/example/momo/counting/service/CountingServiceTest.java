package com.example.momo.counting.service;

import com.example.momo.counting.config.CountingServiceProperties;
import com.example.momo.counting.jpa.entity.Count;
import com.example.momo.counting.jpa.repository.CountingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountingServiceTest {

	static final int SERVICE_NUMBER = 666;

	@Mock
	private CountingRepository countingRepository;

	@Captor
	private ArgumentCaptor<Count> captor;

	private CountingService countingService;

	@Test
	void readsServiceNumber() {
		countingService = new CountingService(
				CountingServiceProperties.builder().serviceNumber(SERVICE_NUMBER).build(),
				null
		);
		assertThat(countingService.serviceNumber()).isEqualTo(SERVICE_NUMBER);
	}

	@Nested
	class GetCurrentValue {
		@Test
		void getFirstTime() {
			when(countingRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
			countingService = new CountingService(null, countingRepository);

			final long result = countingService.getCurrentValue();
			assertThat(result).isEqualTo(0L);
		}

		@Test
		void getExistingValue() {
			final long currentTotal = 743865L;
			when(countingRepository.findAll(any(Pageable.class)))
					.thenReturn(new PageImpl<>(Collections.singletonList(
							Count.builder().count(currentTotal).build()
					)));
			countingService = new CountingService(null, countingRepository);

			final long result = countingService.getCurrentValue();
			assertThat(result).isEqualTo(currentTotal);
		}
	}

	@Nested
	class FirstTimeOperations {
		@BeforeEach
		void setup() {
			when(countingRepository.findAll(any(Pageable.class)))
					.thenReturn(Page.empty());
			when(countingRepository.save(captor.capture()))
					.thenReturn(Count.builder().count(0L).build());

			countingService = new CountingService(null, countingRepository);
		}

		@Test
		void increment() {
			countingService.increment();
			assertThat(captor.getValue().getCount()).isEqualTo(1L);
		}

		@Test
		void decrement() {
			countingService.decrement();
			assertThat(captor.getValue().getCount()).isEqualTo(-1L);
		}
	}

	@Nested
	class OperationsOnExistingValue {
		private final Long currentValue = 4398L;

		@BeforeEach
		void setup() {
			when(countingRepository.findAll(any(Pageable.class))).thenReturn(
					new PageImpl<>(Collections.singletonList(
							Count.builder().count(currentValue).build()
					))
			);
			when(countingRepository.save(captor.capture()))
					.thenReturn(Count.builder().count(0L).build());

			countingService = new CountingService(null, countingRepository);
		}

		@Test
		void incrementExistingTotal() {
			countingService.increment();
			assertThat(captor.getValue().getCount()).isEqualTo(currentValue + 1);
		}

		@Test
		void decrementExistingTotal() {
			countingService.decrement();
			assertThat(captor.getValue().getCount()).isEqualTo(currentValue - 1);
		}
	}
}
