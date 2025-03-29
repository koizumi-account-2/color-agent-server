package com.example.color_agent_server.controller.auth;

import com.example.color_agent_server.controller.AuthApi;
import com.example.color_agent_server.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.openapitools.example.model.LoginUserForm;
import org.openapitools.example.model.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponseDTO> login(LoginUserForm loginUserForm) {
        userService.getUserByEmail(loginUserForm.getEmail());
        return AuthApi.super.login(loginUserForm);
    }
}
