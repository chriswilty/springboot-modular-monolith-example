package com.example.momo.counting.jpa.repository;

import com.example.momo.counting.jpa.entity.Count;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountingRepository extends PagingAndSortingRepository<Count, Long> {
}
