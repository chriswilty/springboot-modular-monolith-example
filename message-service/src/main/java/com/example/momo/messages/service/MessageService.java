package com.example.momo.messages.service;

import com.example.momo.messages.config.MessageServiceProperties;
import com.example.momo.messages.jpa.entity.Message;
import com.example.momo.messages.jpa.repository.MessageRepository;
import com.example.momo.messages.web.model.MessageDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MessageService {

    private final MessageServiceProperties messageServiceProperties;
    private final MessageRepository messageRepository;

    public MessageService(
            final MessageServiceProperties messageServiceProperties,
            final MessageRepository messageRepository
    ) {
        this.messageServiceProperties = messageServiceProperties;
        this.messageRepository = messageRepository;
    }

    public void addMessage(final String message) {
        final Message entity = Message.builder()
                .message(message)
                .createdAt(LocalDateTime.now())
                .build();
        messageRepository.save(entity);
    }

    public List<MessageDTO> getMessages() {
        return StreamSupport.stream(messageRepository.findAll().spliterator(), true)
                .map(entity -> MessageDTO.builder()
                        .message(entity.getMessage())
                        .createdAt(entity.getCreatedAt())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public int serviceNumber() {
        return this.messageServiceProperties.getServiceNumber();
    }

}
