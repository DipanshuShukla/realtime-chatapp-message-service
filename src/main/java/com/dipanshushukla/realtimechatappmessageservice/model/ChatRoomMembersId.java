package com.dipanshushukla.realtimechatappmessageservice.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class ChatRoomMembersId implements Serializable {

    private Long chatId;
    private Long userId;
}
