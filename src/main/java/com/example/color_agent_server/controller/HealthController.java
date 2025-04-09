package com.example.color_agent_server.controller;

import com.example.color_agent_server.util.PresentationWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class HealthController implements HealthApi {

    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String userName;
    @Value("${datasource.password}")
    private String password;
    @Override
    public ResponseEntity<Void> checkHealth() {
        System.out.println(url+"-"+userName+"-"+password);
        return ResponseEntity.ok().build();
    }
}
