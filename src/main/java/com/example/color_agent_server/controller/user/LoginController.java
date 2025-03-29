package com.example.color_agent_server.controller.user;

import com.example.color_agent_server.controller.LoginApi;
import com.example.color_agent_server.security.service.AuthService;
import com.example.color_agent_server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.openapitools.example.model.LoginUserForm;
import org.openapitools.example.model.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LoginController implements LoginApi {
    
    private final UserService userService;
    private final AuthService authService;

    @Override
    public ResponseEntity<UserResponseDTO> login(LoginUserForm loginUserForm) {
        try{
            authService.authenticateUser(loginUserForm);
            return LoginApi.super.login(loginUserForm);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }
}
