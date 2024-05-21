package com.dipanshushukla.realtimechatappmessageservice.dto;

import com.dipanshushukla.realtimechatappmessageservice.entity.ChatRoomMembers;
import com.dipanshushukla.realtimechatappmessageservice.model.ChatRoomMembersId;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomMembersDTO {

    private Long chatId;
    @NotNull(message = "'userId' is required!")
    private Long userId;

    // Static method to convert entity to DTO
    public static ChatRoomMembersDTO fromEntity(ChatRoomMembers entity) {
        return new ChatRoomMembersDTO(entity.getChatRoomMembersId().getChatId(), entity.getChatRoomMembersId().getUserId());
    }

    // Method to convert DTO to entity
    public ChatRoomMembers toEntity() {
        return new ChatRoomMembers(new ChatRoomMembersId(this.chatId, this.userId), null, null); // Assuming you don't need to set ChatRoom and User here
    }
}

