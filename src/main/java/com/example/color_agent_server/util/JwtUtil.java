package com.example.color_agent_server.util;

import com.example.color_agent_server.security.service.CustomUserDetails;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.openapitools.example.model.UserResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.Cookie;
import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class JwtUtil {
    @Getter
    private Long jwtExpirationMs = 3000L * 60 * 60;

    @Value("${encryption.key}")
    private String jwtSecretKey;

    private SecretKey getSignWithKey(){
        byte[] decodedKey = Decoders.BASE64.decode(jwtSecretKey);
        //HS256アルゴリズムに対応する秘密鍵の生成
        return Keys.hmacShaKeyFor(decodedKey);
    }

    // RequestからJWTのCookieを取得する
    public String getJwtFromRequest(HttpServletRequest req){
        if(req.getCookies()==null) return null;
        return  Arrays.stream(req.getCookies())
                .filter(cookie -> "jwt".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }


    //JWTの発行
    public String generateJWT(Authentication auth){
        String email = auth.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
        Map<String, Object> additionalClaims = new HashMap<>();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        additionalClaims.put("roles",userDetails.getAuthorities().stream().map(Object::toString).collect(Collectors.joining(","))); // ユーザーの権限
        additionalClaims.put("id", userDetails.getId()); // UserIDの権限
        return Jwts
                .builder()
                .claims(additionalClaims)
                .subject(email)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSignWithKey(), Jwts.SIG.HS256)
                .compact();
    }

    // JWTの検証
    public boolean validate(String jwt) {
        try{
            Jwts.parser()
                    .verifyWith(getSignWithKey())
                    .build()
                    .parseSignedClaims(jwt);
            return true;
        }catch(IllegalArgumentException | JwtException exception){
            return false;
        }
    }

    //JWTの解読
    public UserResponseDTO getUserInfoFromJWT(String jwt){
        var payload = Jwts.parser()
                .verifyWith(getSignWithKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        return new UserResponseDTO(Long.parseLong(payload.get("id").toString()), Arrays.stream(payload.get("roles").toString().split(",")).toList(),payload.getSubject());
    }
}
