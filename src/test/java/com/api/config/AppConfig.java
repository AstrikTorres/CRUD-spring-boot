package com.api.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@Configuration
@SpringBootApplication(scanBasePackages = "com.testapi")
public class AppConfig {
	
	
}
