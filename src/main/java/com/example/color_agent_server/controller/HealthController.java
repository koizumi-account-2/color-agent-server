package com.example.color_agent_server.controller;

import com.example.color_agent_server.util.PresentationWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class HealthController implements HealthApi {
    @Override
    public ResponseEntity<Void> checkHealth() {
        return ResponseEntity.ok().build();
    }
}
