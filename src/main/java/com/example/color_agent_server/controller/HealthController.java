package com.example.color_agent_server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class HealthController implements HealthApi {
    @Override
    public ResponseEntity<Void> checkHealth() {
        return ResponseEntity.ok().build();
    }
}
