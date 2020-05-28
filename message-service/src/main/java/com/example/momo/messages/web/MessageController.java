package com.example.momo.messages.web;

import com.example.momo.messages.service.MessageService;
import com.example.momo.messages.web.model.MessageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
@RequestMapping("messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(final MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Upload a message to persist in data store")
    public void postMessage(@RequestBody String message) {
        messageService.addMessage(message);
    }

    @GetMapping
    @ApiOperation("Get all persisted messages")
    public ResponseEntity<List<MessageDTO>> getMessages() {
        return ResponseEntity.ok(messageService.getMessages());
    }

    @GetMapping("/sn")
    @ApiOperation("Service number")
    public ResponseEntity<Integer> getServiceNumber() {
        return ResponseEntity.ok(this.messageService.serviceNumber());
    }

}
