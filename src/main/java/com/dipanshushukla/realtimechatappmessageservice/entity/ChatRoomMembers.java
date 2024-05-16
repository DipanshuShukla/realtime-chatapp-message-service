package com.dipanshushukla.realtimechatappmessageservice.entity;

import com.dipanshushukla.realtimechatappmessageservice.model.ChatRoomMembersId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
public class ChatRoomMembers {

    @EmbeddedId
    private ChatRoomMembersId chatRoomMembersId;

    @ManyToOne
    @JoinColumn(name = "chatId", insertable = false, updatable = false)
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;
}
