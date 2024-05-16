package com.dipanshushukla.realtimechatappmessageservice.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.dipanshushukla.realtimechatappmessageservice.model.ChatType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;
    
    private String name;
    private ChatType type;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(columnDefinition = "TEXT")
    private String description;


}
