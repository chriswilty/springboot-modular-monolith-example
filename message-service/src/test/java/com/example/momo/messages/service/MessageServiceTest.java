package com.example.momo.messages.service;

import com.example.momo.messages.config.MessageServiceProperties;
import com.example.momo.messages.jpa.entity.Message;
import com.example.momo.messages.jpa.repository.MessageRepository;
import com.example.momo.messages.web.model.MessageDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

	static final int SERVICE_NUMBER = 777;

	@Mock
	private MessageRepository messageRepository;

	private MessageService messageService;

	@Test
	void readsServiceNumber() {
		messageService = new MessageService(
				MessageServiceProperties.builder().serviceNumber(SERVICE_NUMBER).build(),
				null
		);
		assertThat(messageService.serviceNumber()).isEqualTo(SERVICE_NUMBER);
	}

	@Test
	void savesMessageToRepository() {
		final String message = "any message";
		final ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
		messageService = new MessageService(null, messageRepository);

		messageService.addMessage(message);

		verify(messageRepository).save(messageCaptor.capture());
		assertThat(messageCaptor.getValue().getMessage()).isEqualTo(message);
	}

	@Test
	void getsAllMessagesFromRepository() {
		final String[] inputMessages = { "a message", "another message", "a third message" };
		when(messageRepository.findAll()).thenReturn(
				Arrays.stream(inputMessages)
						.map(message -> Message.builder()
								.message(message)
								.createdAt(LocalDateTime.now())
								.build()
						)
						.collect(Collectors.toList())
		);
		messageService = new MessageService(null, messageRepository);

		final List<String> outputMessages = messageService.getMessages()
				.stream()
				.map(MessageDTO::getMessage)
				.collect(Collectors.toList());

		assertThat(outputMessages).containsExactly(inputMessages);
	}
}
