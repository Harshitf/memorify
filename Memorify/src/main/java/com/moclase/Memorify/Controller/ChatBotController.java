package com.moclase.Memorify.Controller;

import jakarta.annotation.Nonnull;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin("http://localhost:5173")
public class ChatBotController {

   private final ChatClient chatClient;
    @Autowired public  ChatBotController(OllamaChatModel ollamaChatModel) {
        this.chatClient = ChatClient.builder(ollamaChatModel).build();
    }
    @PostMapping("get-answer-from-bot")
    public ResponseEntity<?> giveResponse(@Nonnull @RequestBody String question) {
        String response=chatClient
                            .prompt(question)
                            .call()
                            .content();
        return ResponseEntity.ok(response);
    }

}
