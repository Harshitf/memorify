package com.moclase.Memorify.message;

import com.moclase.Memorify.chat.Chat;
import com.moclase.Memorify.chat.ChatRepository;
import com.moclase.Memorify.file.FileService;
import com.moclase.Memorify.file.FileUtils;
import com.moclase.Memorify.notification.Notification;
import com.moclase.Memorify.notification.NotificationService;
import com.moclase.Memorify.notification.NotificationType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper messageMapper;
    private final FileService fileService;
    private final NotificationService notificationService;
    public void saveMessage(MessageRequest messageRequest) {
        Chat chat = chatRepository.findById(messageRequest.getChatId())
                .orElseThrow(() -> new EntityNotFoundException("Chat not found"));
        Message message = new Message();
        message.setContent(messageRequest.getContent());
        message.setSenderId(messageRequest.getSenderId());
        message.setReceiverId(messageRequest.getReceiverId());
        message.setMessageType(messageRequest.getMessageType());
        message.setChat(chat);
        message.setState(MessageState.SENT);

        messageRepository.save(message);
        Notification notification = Notification.builder()
                .chatId(chat.getId())
                .messageType(messageRequest.getMessageType())
                .content(messageRequest.getContent())
                .senderId(messageRequest.getSenderId())
                .receiverId(messageRequest.getReceiverId())
                .notificationType(NotificationType.MESSAGE)
                .chatName(chat.getChatName(message.getSenderId()))
                .build();
        notificationService.sendNotification(message.getReceiverId(),  notification);

    }

    public List<MessageResponse> findChatMessage(String chatId) {
        return messageRepository.findMessagesByChatId(chatId)
                .stream()
                .map(messageMapper::toMessageResponse)
                .toList();

    }

    @Transactional
    public void setMessagesToSeen(String chatId, Authentication authentication) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("Chat not found"));
        final String recipientId = getRecipientId(chat, authentication);


        messageRepository.setMessagesToSeenByChat(chatId, MessageState.SEEN);
        Notification notification = Notification.builder()
                .chatId(chat.getId())
                .senderId(getSenderId(chat,authentication))
                .receiverId(recipientId)
                .notificationType(NotificationType.SEEN)
                .build();
        notificationService.sendNotification(recipientId,  notification);

    }

    public void uploadMediaMessage(String chatId, MultipartFile file, Authentication authentication) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("Chat not found"));

        final String senderId = getSenderId(chat, authentication);
        final String receiverId = getRecipientId(chat, authentication);
        final String filePath = fileService.saveFile(file, senderId);
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setMessageType(MessageType.IMAGE);
        message.setChat(chat);
        message.setState(MessageState.SENT);
        message.setMediaFilePath(filePath);
        messageRepository.save(message);

        Notification notification = Notification.builder()
                .chatId(chat.getId())
                .messageType(MessageType.IMAGE)
                .senderId(senderId)
                .receiverId(receiverId)
                .notificationType(NotificationType.IMAGE)
                .media(FileUtils.readFileFromLocation(filePath))
                .build();
        notificationService.sendNotification(receiverId,  notification);

    }

    private String getSenderId(Chat chat, Authentication authentication) {
        if (chat.getSender().getId().equals(authentication.getName())) {
            return chat.getSender().getId();
        }
        return chat.getRecipient().getId();
    }

    private String getRecipientId(Chat chat, Authentication authentication) {

        if (chat.getSender().getId().equals(authentication.getName())) {
            return chat.getRecipient().getId();
        }
        return chat.getSender().getId();

    }
}
