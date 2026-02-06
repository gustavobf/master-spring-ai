package com.eazybytes.springai.controller;

import org.springframework.ai.chat.client.*;
import org.springframework.ai.document.*;
import org.springframework.ai.vectorstore.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.*;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api/rag")
public class RAGController {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:/promptTemplates/systemPromptRandomDataTemplate.st")
    Resource promptTemplate;

    public RAGController (@Qualifier("chatMemoryChatClientBuilder") ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @GetMapping("/random/chat")
    public ResponseEntity<String> randomChat (@RequestHeader("username") String username,
                                              @RequestParam("message") String message) {
        SearchRequest searchRequest = SearchRequest.builder().query(message).topK(3).similarityThreshold(0.5).build();
        List<Document> similarDocs = vectorStore.similaritySearch(searchRequest);
        String similarContext = similarDocs.stream().map(Document::getText)
                .collect(Collectors.joining(System.lineSeparator()));
        String answer = chatClient.prompt()
                .system(promptSystemSpec -> promptSystemSpec.text(promptTemplate).param("documents", similarContext))
                .advisors(a -> a.param(CONVERSATION_ID, username)).user(message).call().content();
        return ResponseEntity.ok(answer);
    }

}
