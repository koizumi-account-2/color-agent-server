package com.example.color_agent_server.controller.user;

import com.example.color_agent_server.controller.LoginApi;
import com.example.color_agent_server.security.service.AuthService;
import com.example.color_agent_server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.openapitools.example.model.LoginUserForm;
import org.openapitools.example.model.UserResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.Duration;

@Controller
@RequiredArgsConstructor
public class LoginController implements LoginApi {
    
    private final UserService userService;
    private final AuthService authService;

    @Override
    public ResponseEntity<UserResponseDTO> login(LoginUserForm loginUserForm) {
        try{
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
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }
}
