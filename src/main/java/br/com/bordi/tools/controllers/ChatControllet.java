package br.com.bordi.tools.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;

import br.com.bordi.tools.models.AwnserRecord;
import br.com.bordi.tools.models.QuestionRecord;
import br.com.bordi.tools.services.ChatService;

@RestController
public class ChatControllet {

    @Autowired
    private ChatService chatService;

    @PostExchange("/chat")
    public ResponseEntity<AwnserRecord> chat(
            @RequestBody QuestionRecord question) {
        AwnserRecord answer = chatService.getAwnser(question);
        return ResponseEntity.ok(answer);
    }

}
