package com.eazybytes.springai.advisors;

import org.slf4j.*;
import org.springframework.ai.chat.client.*;
import org.springframework.ai.chat.client.advisor.api.*;
import org.springframework.ai.chat.metadata.*;
import org.springframework.ai.chat.model.*;

public class TokenUsageAuditAdvisor implements CallAdvisor {

    private static final Logger logger = LoggerFactory.getLogger(TokenUsageAuditAdvisor.class);

    @Override
    public ChatClientResponse adviseCall (ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        ChatResponse chatResponse = chatClientResponse.chatResponse();

        if (chatResponse.getMetadata() != null) {
            Usage usage = chatResponse.getMetadata().getUsage();

            if (usage != null) {
                logger.info("Token Usage - Prompt Tokens: {}, Completion Tokens: {}, Total Tokens: {}",
                        usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens());
            } else {
                logger.warn("No token usage information available in the response metadata.");
            }

        }

        return chatClientResponse;
    }

    @Override
    public String getName () {
        return "TokenUsageAuditAdvisor";
    }

    @Override
    public int getOrder () {
        return 1;
    }
}
