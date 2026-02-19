package com.eazybytes.springai.controller;

import org.springframework.ai.chat.client.*;
import org.springframework.ai.chat.client.advisor.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    @Value("classpath:/promptTemplates/hrPolicy.st")
    Resource hrPolicyTemplate;

    public ChatController (ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    @GetMapping("/chat")
    public String chat (@RequestParam("message") String message) {
        return chatClient.prompt().user(message).call().content();
    }

    @GetMapping("/prompt-stuffing")
    public String promptStuffing (@RequestParam("message") String message) {
        return chatClient.prompt().system(hrPolicyTemplate).user(message).call().content();
    }

}