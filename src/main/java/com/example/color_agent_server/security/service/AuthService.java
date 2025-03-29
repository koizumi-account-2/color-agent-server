package com.example.color_agent_server.security.service;

import com.example.color_agent_server.controller.user.AuthenticateResponse;
import com.example.color_agent_server.controller.user.AuthenticateResponse;
import lombok.RequiredArgsConstructor;
import org.openapitools.example.model.LoginUserForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManagerWithDB;
    // ログイン時にユーザを認証する
    public AuthenticateResponse authenticateUser(LoginUserForm loginUserForm){
        Authentication auth = authenticationManagerWithDB.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserForm.getEmail(),
                        loginUserForm.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        return new AuthenticateResponse("AA",123L,"adasda");

    }
}
