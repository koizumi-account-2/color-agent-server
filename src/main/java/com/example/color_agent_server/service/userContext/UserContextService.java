package com.example.color_agent_server.service.userContext;

import com.example.color_agent_server.repository.userContext.UserContextRecord;
import com.example.color_agent_server.repository.userContext.UserContextRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.example.model.UserContextEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserContextService {
    private final UserContextRepository userContextRepository;

    public Optional<UserContextEntity> getUserContext(long id){
        return userContextRepository.findById(id).map(x -> new UserContextEntity(x.id(),x.userContext(),x.totalTokens()));
    }

    public void createUserContext(UserContextRecord userContextRecord){
        userContextRepository.create(userContextRecord);
    }

    public void updateUserContext(UserContextRecord userContextRecord){
        userContextRepository.update(userContextRecord);
    }
}
