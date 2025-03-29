package com.example.color_agent_server.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // リクエストの前処理
        System.out.println("Custom Filter - Before Request");
        // ダミーユーザ
        String username = "demoUser";

        // ダミーユーザに与えるロール（権限）
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

        // 認証済みトークンを作成
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
        // SecurityContextにAuthentication情報を設定
        // SecurityContextHolder.getContext().setAuthentication(auth);

        //filterを続行
        filterChain.doFilter(request,response);

        // レスポンスの後処理
        System.out.println("Custom Filter - After Response");
    }
}
