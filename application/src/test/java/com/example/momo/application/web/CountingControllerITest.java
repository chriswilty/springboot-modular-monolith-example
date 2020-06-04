package com.example.momo.application.web;

import com.example.momo.counting.jpa.repository.CountingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        properties = { "counting.datasource.url=jdbc:h2:mem:testdb" },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class CountingControllerITest {

    @LocalServerPort
    private int port;

    @Autowired
    private CountingRepository countingRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Nested
    class SmokeTests {
        @Test
        void contextLoads() {
            assertThat(countingRepository).isNotNull();
        }
    }

    @Nested
    class CountingTests {
        final String urlCount = baseUrl();
        final String urlPlus = url("plus");
        final String urlMinus = url("minus");

        @AfterEach
        void resetDatabase() {
            countingRepository.deleteAll();
        }

        @Test
        void getInitialCount() {
            final Long result = get(urlCount);
            assertThat(result).isEqualTo(0L);
        }

        @Test
        void incrementFirstTimeCall() {
            final Long result = post(urlPlus);
            assertThat(result).isEqualTo(1L);
        }

        @Test
        void decrementFirstTimeCall() {
            final Long result = post(urlMinus);
            assertThat(result).isEqualTo(-1L);
        }

        @Test
        void getCountAfterIncrement() {
            post(urlPlus);

            final Long result = get(urlCount);
            assertThat(result).isEqualTo(1L);
        }

        @Test
        void getCountAfterDecrement() {
            post(urlMinus);

            final Long result = get(urlCount);
            assertThat(result).isEqualTo(-1L);
        }

        @Test
        void incrementTwice() {
            post(urlPlus);

            final Long result = post(urlPlus);
            assertThat(result).isEqualTo(2L);
        }

        @Test
        void decrementTwice() {
            post(urlMinus);

            final long result = post(urlMinus);
            assertThat(result).isEqualTo(-2L);
        }
    }

    private Long get(final String url) {
        return restTemplate.getForObject(url, Long.class);
    }

    private long post(final String url) {
        return restTemplate.postForObject(url, null, Long.class);
    }

    private String baseUrl() {
        return "http://localhost:" + port + "/count";
    }

    private String url(final String suffix) {
        return baseUrl() + "/" + suffix;
    }
}
