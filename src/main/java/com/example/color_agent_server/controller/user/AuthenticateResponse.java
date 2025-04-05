package com.example.color_agent_server.controller.user;


import lombok.Getter;
import org.openapitools.example.model.UserResponseDTO;

import java.util.List;

// 認証結果
@Getter
public class AuthenticateResponse {
    private final UserResponseDTO userResponseDTO;
    private final String jwt;

    public AuthenticateResponse(List<String> role, Long userID, String email, String jwt){
        this.userResponseDTO = new UserResponseDTO(userID,role,email);
        this.jwt = jwt;
    }
}
