package com.dipanshushukla.realtimechatappmessageservice.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.dipanshushukla.realtimechatappmessageservice.model.ChatRoomType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ChatRoom {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;
    
    private String name;
    @Enumerated(EnumType.STRING)
    private ChatRoomType type;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(columnDefinition = "TEXT")
    private String description;

    public ChatRoom(String name, ChatRoomType type, String description){
        this.name = name;
        this.type = type;
        this.description = description;
    }
}
