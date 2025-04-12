package com.example.color_agent_server.controller;

import com.example.color_agent_server.util.PresentationWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class HealthController implements HealthApi {
    @Value("${allowedOrigins}")
    private String allow;
    @Override
    public ResponseEntity<Void> checkHealth() {
        System.out.println("ARROW"+allow);
        return ResponseEntity.ok().build();
    }
}
