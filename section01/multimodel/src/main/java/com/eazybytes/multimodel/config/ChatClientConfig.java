package com.eazybytes.multimodel.config;

import org.springframework.ai.chat.client.*;
import org.springframework.ai.ollama.*;
import org.springframework.ai.openai.*;
import org.springframework.context.annotation.*;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient openAiChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.create(openAiChatModel);
    }

    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
        ChatClient.Builder chatClientBuilder = ChatClient.builder(ollamaChatModel);
        return chatClientBuilder.build();
    }

}
