package com.eazybytes.springai.controller;


import org.springframework.ai.chat.client.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

@RestController
@RequestMapping("/api")
public class StreamController {

    private final ChatClient chatClient;

    public StreamController (ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/stream")
    public Flux<String> stream (@RequestParam("message") String message) {
        return chatClient.prompt().user(message).stream().content();
    }
}
