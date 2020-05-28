package com.example.momo.messages.web.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class MessageDTO {
    String message;
    LocalDateTime createdAt;
}
