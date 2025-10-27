package com.moclase.Memorify.chat;

import com.moclase.Memorify.user.User;
import com.moclase.Memorify.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ChatResponse> getChatByReceiverId(Authentication currentUser){
        final String userId = currentUser.getName();
        return chatRepository.findChatsBySenderId(userId)
                .stream()
                .map(c->chatMapper.toChatResponse(c,userId))
                .toList();

    }
    public String createChat(String senderId, String receiverId){
        Optional<Chat> existingChat=chatRepository.findChatsByRecieverAndSender(senderId,receiverId);
        if(existingChat.isPresent()){
            return existingChat.get().getId();
        }
        User sender=userRepository.findByPublicId(senderId)
                .orElseThrow(()->new EntityNotFoundException("user with id "+senderId+" not found"));
        User reciever=userRepository.findByPublicId(receiverId)
                .orElseThrow(()->new EntityNotFoundException("user with id "+receiverId+" not found"));
        Chat chat=new  Chat();
        chat.setSender(sender);

        Chat savedChat=chatRepository.save(chat);
        return savedChat.getId();
    }
}
