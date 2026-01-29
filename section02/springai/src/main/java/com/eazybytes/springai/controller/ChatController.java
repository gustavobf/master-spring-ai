package com.eazybytes.springai.controller;

import com.eazybytes.springai.advisors.*;
import org.springframework.ai.chat.client.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController (ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String chat (@RequestParam("message") String message) {
        return chatClient.prompt()
                //                .advisors(new TokenUsageAuditAdvisor())
                .user(message).call().content();
    }
}
