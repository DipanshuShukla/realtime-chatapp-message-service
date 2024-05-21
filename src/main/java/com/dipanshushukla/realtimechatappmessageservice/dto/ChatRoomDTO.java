package com.dipanshushukla.realtimechatappmessageservice.dto;

import java.sql.Timestamp;

import com.dipanshushukla.realtimechatappmessageservice.entity.ChatRoom;
import com.dipanshushukla.realtimechatappmessageservice.model.ChatRoomType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDTO {

    private Long chatId;
    @NotNull(message = "'name' is required!")
    private String name;
    @NotNull(message = "'type' is required!")
    private ChatRoomType type;
    private Timestamp createdAt;
    @NotNull(message = "'description' is required!")
    private String description;

    // Static method to convert entity to DTO
    public static ChatRoomDTO fromEntity(ChatRoom entity) {
        return new ChatRoomDTO(entity.getChatId(), entity.getName(), entity.getType(), entity.getCreatedAt(), entity.getDescription());
    }

    // Method to convert DTO to entity
    public ChatRoom toEntity() {
        return new ChatRoom(this.name, this.type, this.description);
    }
}


