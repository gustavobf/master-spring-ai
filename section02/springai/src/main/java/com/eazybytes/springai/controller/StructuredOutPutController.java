package com.eazybytes.springai.controller;

import com.eazybytes.springai.model.*;
import org.springframework.ai.chat.client.*;
import org.springframework.ai.chat.client.advisor.*;
import org.springframework.ai.converter.*;
import org.springframework.core.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/api")
public class StructuredOutPutController {

    private final ChatClient chatClient;

    public StructuredOutPutController (ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    @GetMapping("/chat-bean")
    public ResponseEntity<CountryCities> chatBean (@RequestParam("message") String message) {
        CountryCities countryCities = chatClient.prompt().user(message).call().entity(CountryCities.class);
        return ResponseEntity.ok(countryCities);
    }

    @GetMapping("/chat-list")
    public ResponseEntity<List<String>> chatList (@RequestParam("message") String message) {
        List<String> countryCities = chatClient.prompt().user(message).call().entity(new ListOutputConverter());
        return ResponseEntity.ok(countryCities);
    }

    @GetMapping("/chat-map")
    public ResponseEntity<Map<String, Object>> chatMap (@RequestParam("message") String message) {
        Map<String, Object> countryCities = chatClient.prompt().user(message).call().entity(new MapOutputConverter());
        return ResponseEntity.ok(countryCities);
    }

    @GetMapping("/chat-bean-list")
    public ResponseEntity<List<CountryCities>> chatBeanList (@RequestParam("message") String message) {
        List<CountryCities> countryCities = chatClient.prompt().user(message).call()
                .entity(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(countryCities);
    }

}
