package com.example.color_agent_server.security.filter;

import com.example.color_agent_server.security.service.CustomUserDetailsService;
import com.example.color_agent_server.service.user.UserService;
import com.example.color_agent_server.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = jwtUtil.getJwtFromRequest(request);
            if(jwt != null && jwtUtil.validate(jwt)){
                var userInfo = jwtUtil.getUserInfoFromJWT(jwt);
                //UserDetails user = userDetailsService.loadUserByUsername(email);
                // ユーザーの情報を持つAuthenticationオブジェクトを作成
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userInfo.getEmail(), null,List.of(new SimpleGrantedAuthority(userInfo.getRole())));
                // SecurityContextにAuthentication情報を設定
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            //filterを続行
            filterChain.doFilter(request,response);
        }catch(RuntimeException exception){
            // RuntimeExceptionが発生した場合、セキュリティコンテキストをクリア
            SecurityContextHolder.clearContext();

            //HttpServletResponse.SC_UNAUTHORIZED
            throw  exception;
            // エラーレスポンスを処理
            //handleErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        }catch(Exception exception){
            // RuntimeExceptionが発生した場合、セキュリティコンテキストをクリア
            SecurityContextHolder.clearContext();
            throw  exception;
            // エラーレスポンスを処理
            //handleErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        }


    }
}
