package com.eazybytes.springai;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.retry.annotation.*;

@SpringBootApplication
@EnableRetry
public class SpringAiApplication {

    public static void main (String[] args) {
        SpringApplication.run(SpringAiApplication.class, args);
    }

}
