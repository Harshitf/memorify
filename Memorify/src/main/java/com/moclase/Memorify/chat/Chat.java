package com.moclase.Memorify.chat;

import com.moclase.Memorify.common.BaseAuditingEntity;
import com.moclase.Memorify.message.Message;
import com.moclase.Memorify.message.MessageState;
import com.moclase.Memorify.message.MessageType;
import com.moclase.Memorify.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="chat")
@NamedQuery(name=ChatConstants.FIND_CHAT_BY_SENDER_ID,
            query = "SELECT DISTINCT c FROM Chat c WHERE c.sender.id= :senderID OR c.recipient.id=:senderId ORDER BY createdDate DESC ")
@NamedQuery(name = ChatConstants.FIND_CHAT_BY_SENDER_ID_AND_RECEIVER,
            query = "SELECT DISTINCT c FROM Chat c WHERE (c.sender.id=:senderId AND c.recipient.id=:recipentId)OR(c.sender.id=:recipientId AND c.recipient.id=:senderId)")
public class Chat extends BaseAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name="sender_id",nullable = false)
    private User sender;
    @ManyToOne
    @JoinColumn(name = "recipient_id",nullable = false)
    private User recipient;
    @OneToMany(mappedBy = "chat",fetch = FetchType.EAGER)
    @OrderBy("createdDate desc ")
    private List<Message> messages;
    @Transient
    public String getChatName(String senderId) {
        if (recipient.getId().equals(senderId))
            return sender.getFirstName() + " " + sender.getLastName();
        return recipient.getFirstName() + " " + recipient.getLastName();
    }
    @Transient
    public long getUnreadMessages(final String senderId) {
        return messages.stream()
                .filter(m->m.getReceiverId().equals(senderId))
                .filter(m-> MessageState.SENT==m.getState())
                .count();
    }
    @Transient
    public String getLastMessage() {
        if(messages!=null&&!messages.isEmpty()){
             if (messages.getFirst().getMessageType()!= MessageType.TEXT){
                 return "Attachment";
             }
             return messages.getFirst().getContent();
        }
        return null;
    }

    @Transient
    public LocalDateTime getLastMessageTime(String senderId) {
        if(messages!=null&&!messages.isEmpty()){
            return messages.getFirst().getCreatedDate();
        }
        return null;
    }
}

