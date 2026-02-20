package com.eazybytes.springai.tools;

import org.slf4j.*;
import org.springframework.ai.tool.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;

@Component
public class TimeTools {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTools.class);

    @Tool(name = "getCurrentLocalTime", description = "Get the current time in the user's timezone")
    String getCurrentLocalTime () {
        LOGGER.info("Returning the current time in the user's timezone");
        return LocalTime.now().toString();
    }

    @Tool(name = "getCurrentTime", description = "Get the current time in the specified time zone.")
    public String getCurrentTime (@ToolParam(description = "Value representing the time zone") String timeZone) {
        LOGGER.info("Returning the current time in the timezone {}", timeZone);
        return LocalTime.now(ZoneId.of(timeZone)).toString();
    }
}