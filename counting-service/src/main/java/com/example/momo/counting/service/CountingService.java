package com.example.momo.counting.service;

import com.example.momo.counting.config.CountingServiceProperties;
import com.example.momo.counting.jpa.entity.Count;
import com.example.momo.counting.jpa.repository.CountingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CountingService {

    private static final Pageable latestResult = PageRequest.of(0, 1, Sort.Direction.DESC, "updatedAt");
    private final CountingServiceProperties countingServiceProperties;
    private final CountingRepository countingRepository;

    public CountingService(
            final CountingServiceProperties countingServiceProperties,
            final CountingRepository countingRepository
    ) {
        this.countingServiceProperties = countingServiceProperties;
        this.countingRepository = countingRepository;
    }

    public long increment() {
        return add(1);
    }

    public long decrement() {
        return add(-1);
    }

    public long getCurrentValue() {
        return findLatest().map(Count::getCount).orElse(0L);
    }

    public int serviceNumber() {
        return this.countingServiceProperties.getServiceNumber();
    }

    // NOTE: Race-condition alert! In a real application we might use event-sourcing
    // to record arithmetic events, and replay them to get the current total.
    private long add(final long addition) {
        final Count next = Count.builder()
                .count(getCurrentValue() + addition)
                .updatedAt(LocalDateTime.now())
                .build();

        return countingRepository.save(next).getCount();
    }

    private Optional<Count> findLatest() {
        return countingRepository.findAll(latestResult).get().findFirst();
    }

}
