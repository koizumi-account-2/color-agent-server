package com.example.color_agent_server.controller.user;


import lombok.Getter;
import org.openapitools.example.model.UserResponseDTO;

// 認証結果
@Getter
public class AuthenticateResponse {
    private final UserResponseDTO userResponseDTO;

    public AuthenticateResponse(String role,Long userID,String email){
        this.userResponseDTO = new UserResponseDTO(userID,role,email);
    }
}
