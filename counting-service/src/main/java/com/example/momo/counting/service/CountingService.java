package com.example.momo.counting.service;

import com.example.momo.counting.config.CountingServiceProperties;
import com.example.momo.counting.jpa.entity.Count;
import com.example.momo.counting.jpa.repository.CountingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CountingService {

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

    public long getCount() {
        final Optional<Count> entityOpt = countingRepository.findById(1L);
        return entityOpt.isPresent() ? entityOpt.get().getCount() : 0;
    }

    public int serviceNumber() {
        return this.countingServiceProperties.getServiceNumber();
    }

    private long add(final int addition) {
        final Count entity = countingRepository.findById(1L).orElse(Count.builder().count(0L).build());

        entity.setCount(entity.getCount() + addition);
        entity.setUpdatedAt(LocalDateTime.now());
        countingRepository.save(entity);

        return entity.getCount();
    }

}
