package com.example.color_agent_server.controller.userContext;

import com.example.color_agent_server.controller.UserApi;
import com.example.color_agent_server.repository.userContext.UserContextRecord;
import com.example.color_agent_server.service.userContext.UserContextService;
import lombok.RequiredArgsConstructor;
import org.openapitools.example.model.UpdateTotalTokensRequest;
import org.openapitools.example.model.UpdateUserContextRequest;
import org.openapitools.example.model.UserContextEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserContextController implements UserApi {

    private final UserContextService userContextService;
    //http://localhost:8080/user?email=admin@example.com


    @Override
    public ResponseEntity<UserContextEntity> getUserContext(Long id) {
        UserContextEntity userContextEntity = userContextService.getUserContext(id)
                .orElseGet(() -> {
                    var newEntity =new UserContextEntity(id,"",0L);
                    userContextService.createUserContext(new UserContextRecord(id,"",0L));
                    return newEntity;
                });
        return ResponseEntity.ok().body(userContextEntity);
    }

    @Override
    public ResponseEntity<UserContextEntity> updateTotalTokens(Long id, UpdateTotalTokensRequest updateTotalTokensRequest) {
        var updateRecord = new UserContextRecord(id,null,updateTotalTokensRequest.getTotalTokens());
        return ResponseEntity.ok().body(update(updateRecord));
    }

    @Override
    public ResponseEntity<UserContextEntity> updateUserContext(Long id, UpdateUserContextRequest updateUserContextRequest) {
        var updateRecord = new UserContextRecord(id,updateUserContextRequest.getUserContext(),null);
        return ResponseEntity.ok().body(update(updateRecord));
    }


    private UserContextEntity update(UserContextRecord record){
        if(userContextService.getUserContext(record.id()).isPresent()){
            userContextService.updateUserContext(record);
        }else{
            userContextService.createUserContext(record);
        }
        return userContextService.getUserContext(record.id()).orElseThrow(()->new RuntimeException("消えた"));
    }
}
