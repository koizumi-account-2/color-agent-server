package com.example.color_agent_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;

@SpringBootApplication
public class ColorAgentServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColorAgentServerApplication.class, args);
	}

}
