package com.example.color_agent_server.security.service;

import com.example.color_agent_server.repository.user.UserRepository;
import com.example.color_agent_server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userDate = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        //.authorities(Collections.singleton(new SimpleGrantedAuthority("AA")))
        return org.springframework.security.core.userdetails.User.builder()
                .username(userDate.email())
                .password(userDate.password())
                //.authorities(Collections.singleton(new SimpleGrantedAuthority("AA")))
                .build();
    }
}
