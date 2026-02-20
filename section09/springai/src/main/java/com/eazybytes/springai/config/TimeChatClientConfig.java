package com.eazybytes.springai.config;

import com.eazybytes.springai.advisors.*;
import com.eazybytes.springai.tools.*;
import org.springframework.ai.chat.client.*;
import org.springframework.ai.chat.client.advisor.*;
import org.springframework.ai.chat.client.advisor.api.*;
import org.springframework.ai.chat.memory.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class TimeChatClientConfig {

    @Bean("timeChatClient")
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder,
                                 ChatMemory chatMemory, TimeTools timeTools) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor tokenUsageAdvisor = new TokenUsageAuditAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return chatClientBuilder
                .defaultTools(timeTools)
                .defaultAdvisors(List.of(loggerAdvisor, memoryAdvisor,tokenUsageAdvisor))
                .build();
    }
}