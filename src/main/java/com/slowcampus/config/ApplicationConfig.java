package com.slowcampus.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.slowcampus.dao",
        "com.slowcampus.service"})
@Import({ DBConfig.class})
public class ApplicationConfig {
}
