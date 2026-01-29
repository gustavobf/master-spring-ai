package com.eazybytes.springai.controller;

import org.springframework.ai.chat.client.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PromptStuffingController {

    private final ChatClient chatClient;

    public PromptStuffingController (ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    Resource systemPromptTemplate;

    @GetMapping("/prompt-stuffing")
    public String promptStuffing (@RequestParam("message") String message) {
        return chatClient.prompt().system(systemPromptTemplate).user(message).call().content();
    }
}
