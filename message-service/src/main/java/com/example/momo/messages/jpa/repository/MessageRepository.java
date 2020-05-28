package com.example.momo.messages.jpa.repository;

import com.example.momo.messages.jpa.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}
