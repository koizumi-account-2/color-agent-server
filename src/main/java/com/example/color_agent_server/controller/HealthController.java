package com.example.color_agent_server.controller;

import com.example.color_agent_server.util.PresentationWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class HealthController implements HealthApi {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Override
    public ResponseEntity<Void> checkHealth() {
        System.out.println(url+"-"+userName+"-"+password);
        return ResponseEntity.ok().build();
    }
}
