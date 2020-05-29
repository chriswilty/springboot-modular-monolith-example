package com.example.momo.counting.jpa.repository;

import com.example.momo.counting.jpa.entity.Count;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountingRepository extends CrudRepository<Count, Long> {
}
