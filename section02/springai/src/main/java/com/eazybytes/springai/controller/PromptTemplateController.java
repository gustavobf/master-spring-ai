package com.eazybytes.springai.controller;

import org.springframework.ai.chat.client.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PromptTemplateController {

    private final ChatClient chatClient;

    public PromptTemplateController (ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Value("classpath:/promptTemplates/userPromptTemplate.st")
    Resource userPromptTemplate;

    @GetMapping("/email")
    public String chat (@RequestParam("customerName") String customerName,
                        @RequestParam("customerMessage") String customerMessage) {
        return chatClient.prompt().system("""
                You are a professional customer service assistant which helps drafting email
                responses to improve the productivity of the customer support team
                """).user(promptTemplateSpec -> promptTemplateSpec.text(userPromptTemplate)
                .param("customerName", customerName).param("customerMessage", customerMessage)).call().content();
    }
}
