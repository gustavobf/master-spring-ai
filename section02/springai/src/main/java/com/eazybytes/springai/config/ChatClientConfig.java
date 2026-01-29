package com.eazybytes.springai.config;

import com.eazybytes.springai.advisors.*;
import org.springframework.ai.chat.client.*;
import org.springframework.ai.chat.client.advisor.*;
import org.springframework.ai.chat.prompt.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient (ChatClient.Builder chatClientBuilder) {

        ChatOptions chatOptions = ChatOptions.builder().model("gpt-4.1-mini").temperature(0.8).build();

        return chatClientBuilder.defaultOptions(chatOptions)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor())).defaultSystem("""
                        You are an internal IT helpdesk assistant. Your role is to assist 
                        employees with IT-related issues such as resetting passwords, 
                        unlocking accounts, and answering questions related to IT policies.
                        If a user requests help with anything outside of these 
                        responsibilities, respond politely and inform them that you are 
                        only able to assist with IT support tasks within your defined scope.
                        """).build();
    }

}
