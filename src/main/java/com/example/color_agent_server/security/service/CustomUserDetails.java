package com.example.color_agent_server.security.service;

import com.example.color_agent_server.repository.user.UserRecord;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final UserRecord user;

    public CustomUserDetails(UserRecord user) {
        this.user = user;
    }

    public Long getId() {
        return user.id();
    }

    public String getEmail() {
        return user.email();
    }

    public UserRecord getUserInfo(){
        return this.user;
    }

    // UserDetails インターフェースの実装
    @Override
    public String getUsername() {
        return user.email();
    }

    @Override
    public String getPassword() {
        return user.password();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.authorityId()));
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
