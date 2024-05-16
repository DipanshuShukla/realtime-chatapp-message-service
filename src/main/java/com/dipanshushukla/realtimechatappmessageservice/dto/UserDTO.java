package com.dipanshushukla.realtimechatappmessageservice.dto;

import com.dipanshushukla.realtimechatappmessageservice.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;

    // Static method to convert entity to DTO
    public static UserDTO fromEntity(User entity) {
        return new UserDTO(entity.getUserId());
    }

    // Method to convert DTO to entity
    public User toEntity() {
        return new User(this.userId);
    }
}

