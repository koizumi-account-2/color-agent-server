package com.example.color_agent_server.controller.auth;

import com.example.color_agent_server.controller.AuthApi;
import com.example.color_agent_server.security.service.AuthService;
import com.example.color_agent_server.security.service.CustomUserDetails;
import com.example.color_agent_server.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.openapitools.example.model.LoginUserForm;
import org.openapitools.example.model.UserResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.time.Duration;

@Controller
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<UserResponseDTO> checkAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserResponseDTO user = (UserResponseDTO) auth.getPrincipal();
        return ResponseEntity.ok().body(user);
    }

    @Override
    public ResponseEntity<UserResponseDTO> login(LoginUserForm loginUserForm) {
        var result = authService.authenticateUser(loginUserForm);
        ResponseCookie cookie = ResponseCookie.from("jwt", result.getJwt())
                .httpOnly(true)                // HttpOnly属性を有効にする
                .secure(false)                 // HTTPS通信のみ（開発時はfalseに設定可能）
                .path("/")                    // クッキーが有効なパス
                .maxAge(Duration.ofHours(1))  // 有効期限（1時間）
                .sameSite("Lax")           // CSRF対策
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(result.getUserResponseDTO());
    }

    @Override
    public ResponseEntity<Void> logout() {
        ResponseCookie cookie = ResponseCookie.from("jwt","")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(Duration.ofHours(1))
                .sameSite("Lax")
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }
}
//
//var auth = SecurityContextHolder.getContext().getAuthentication();
//        return ResponseEntity.ok().build();