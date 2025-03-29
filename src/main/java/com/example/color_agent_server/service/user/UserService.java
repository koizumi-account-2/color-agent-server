package com.example.color_agent_server.service.user;

import com.example.color_agent_server.repository.user.UserRecord;
import com.example.color_agent_server.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserRecord getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(email));
    }
}
