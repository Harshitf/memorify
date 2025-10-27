package com.moclase.Memorify.message;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {

    private Long id;
    private String content;
    private String senderId;
    private String receiverId;
    private MessageType messageType;
    private MessageState messageState;

    private LocalDateTime createdAt;
    private byte[] media;
}
