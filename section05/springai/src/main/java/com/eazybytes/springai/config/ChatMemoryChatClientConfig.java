package com.eazybytes.springai.config;

import com.eazybytes.springai.advisors.*;
import org.springframework.ai.chat.client.*;
import org.springframework.ai.chat.client.advisor.*;
import org.springframework.ai.chat.client.advisor.api.*;
import org.springframework.ai.chat.memory.*;
import org.springframework.ai.chat.memory.repository.jdbc.*;
import org.springframework.ai.chat.prompt.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class ChatMemoryChatClientConfig {

    @Bean
    ChatMemory chatMemory (JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        return MessageWindowChatMemory.builder().maxMessages(10).chatMemoryRepository(jdbcChatMemoryRepository).build();
    }

    @Bean(name = "chatMemoryChatClientBuilder")
    public ChatClient chatClient (ChatClient.Builder chatClientBuilder, ChatMemory chatMemory) {
        Advisor simpleLoggerAdvisor = new SimpleLoggerAdvisor();
        Advisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return chatClientBuilder.defaultAdvisors(List.of(simpleLoggerAdvisor, messageChatMemoryAdvisor)).build();
    }

}
