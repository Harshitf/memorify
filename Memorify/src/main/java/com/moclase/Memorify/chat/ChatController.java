package com.moclase.Memorify.chat;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@AllArgsConstructor
@Tag(name="Chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<String> createChat(
            @RequestParam(name = "sender-id") String senderId,
            @RequestParam(name = "reciever-id") String recieverId
    ) {
        final String chatId = chatService.createChat(senderId, recieverId);
        return ResponseEntity.ok(chatId);
    }
    @GetMapping
    public ResponseEntity<List<ChatResponse>> getChatsByReciever(Authentication authentication) {
        return ResponseEntity.ok(chatService.getChatByReceiverId(authentication));
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public String send(String message) {
        return message;
    }
    
}
