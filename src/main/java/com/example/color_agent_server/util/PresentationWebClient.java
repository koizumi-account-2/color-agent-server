package com.example.color_agent_server.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PresentationWebClient {
    private final WebClient webClient;

    public PresentationWebClient(){
        this.webClient = WebClient.builder().baseUrl("http://fastapi-alb-1229098353.ap-northeast-1.elb.amazonaws.com:80").build();
    }

    public void get(){
        var result =webClient
                .get()
                .uri("/health")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                        .bodyToMono(String.class)
                                .subscribe(
                                        response -> System.out.println("成功"+response),
                                        error -> System.out.println("失敗"+error)
                                );
        System.out.println(result);
    }
}
