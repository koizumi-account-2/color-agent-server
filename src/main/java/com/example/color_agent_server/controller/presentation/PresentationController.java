package com.example.color_agent_server.controller.presentation;

import com.example.color_agent_server.controller.PresentationApi;
import lombok.RequiredArgsConstructor;
import org.openapitools.example.model.InterviewState;
import org.openapitools.example.model.Persona;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PresentationController implements PresentationApi {


    @Override
    public ResponseEntity<InterviewState> createPersona(InterviewState interviewState) {
        List<Persona> personasList = List.of(
                new Persona("AAAAAAAA","お青青々青々青々あおあ"),
                new Persona("BBBBBB","時事ジジイ一っじじじじ"));
        interviewState.setPersonaList(personasList);
        return ResponseEntity.created(URI.create("/persona")).body(interviewState);
    }

    @Override
    public ResponseEntity<InterviewState> confirmPersona(InterviewState interviewState) {

        if(interviewState.getPersonaConfirmed()){
            return ResponseEntity.ok().body(interviewState);
        }else {
            // ペルソナの再生成
            return ResponseEntity.ok().body(interviewState);
        }

    }
}
