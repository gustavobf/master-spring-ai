package com.eazybytes.mcpserverremote.config;

import com.eazybytes.mcpserverremote.tool.*;
import org.springframework.ai.support.*;
import org.springframework.ai.tool.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class McpServerConfig {

    @Bean
    List<ToolCallback> toolCallbacks (HelpDeskTools helpDeskTools) {
        return List.of(ToolCallbacks.from(helpDeskTools));
    }
}