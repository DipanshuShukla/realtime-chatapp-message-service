package com.dipanshushukla.realtimechatappmessageservice.dto;

import java.sql.Timestamp;

import com.dipanshushukla.realtimechatappmessageservice.entity.Message;
import com.dipanshushukla.realtimechatappmessageservice.model.MessageStatus;
import com.dipanshushukla.realtimechatappmessageservice.model.MessageType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private Long messageId;
    private Long chatRoomId;
    private Long userId;
    private String content;
    private Timestamp timestamp;
    private MessageStatus status;
    private MessageType type;

    // Static method to convert entity to DTO
    public static MessageDTO fromEntity(Message entity) {
        return new MessageDTO(entity.getMessageId(), entity.getChatRoom().getChatId(), entity.getUser().getUserId(), entity.getContent(), entity.getTimestamp(), entity.getStatus(), entity.getType());
    }

    // Method to convert DTO to entity
    public Message toEntity() {
        return new Message(this.messageId, null, null, this.content, this.timestamp, this.status, this.type);
    }
}

