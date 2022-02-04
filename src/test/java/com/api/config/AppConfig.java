package com.api.config;

import org.mockito.Mock;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.api.services.ConnectionService;

@EnableWebMvc
@Configuration
@SpringBootApplication(scanBasePackages = "com.testapi")
public class AppConfig {
	
	@Mock
	public ConnectionService connectionService;

}
