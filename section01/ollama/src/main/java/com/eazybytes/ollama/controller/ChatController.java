package com.eazybytes.ollama.controller;

import org.springframework.ai.chat.client.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat (@RequestParam("message") String message) {
        return chatClient.prompt(message).call().content();
    }

}
