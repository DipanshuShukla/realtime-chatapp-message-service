package com.dipanshushukla.realtimechatappmessageservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipanshushukla.realtimechatappmessageservice.dto.ChatRoomDTO;
import com.dipanshushukla.realtimechatappmessageservice.entity.ChatRoom;
import com.dipanshushukla.realtimechatappmessageservice.entity.ChatRoomMembers;
import com.dipanshushukla.realtimechatappmessageservice.entity.User;
import com.dipanshushukla.realtimechatappmessageservice.repository.ChatRoomMembersRepository;
import com.dipanshushukla.realtimechatappmessageservice.repository.ChatRoomRepository;
import com.dipanshushukla.realtimechatappmessageservice.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomMembersRepository chatRoomMembersRepository;

    public void createChatRoom(ChatRoomDTO chatRoomDTO ){        
        ChatRoom chatRoom = new ChatRoom(chatRoomDTO.getName(), chatRoomDTO.getType(), chatRoomDTO.getDescription());
        repository.save(chatRoom);
    }

    public ChatRoomDTO getChatRoomById(Long chatRoomId) throws EntityNotFoundException{
        ChatRoom chatRoom = repository.findById(chatRoomId).orElseThrow(() -> new EntityNotFoundException("No chat room found by id: " + chatRoomId));
        return ChatRoomDTO.fromEntity(chatRoom);
    }

    public void updateChatRoom(Long chatRoomId, ChatRoomDTO chatRoomDTO) throws IllegalArgumentException, EntityNotFoundException{
        if (chatRoomDTO.getName() == null && chatRoomDTO.getType() == null && chatRoomDTO.getDescription() == null){
            throw new IllegalArgumentException("At least one field must be supplied to update the chat room.");
        }

        ChatRoom chatRoom = repository.findById(chatRoomId).orElseThrow(() -> new EntityNotFoundException("No chat room found by id: " + chatRoomId));

        if (chatRoomDTO.getType() != null) chatRoom.setType(chatRoomDTO.getType());
        if (chatRoomDTO.getName() != null) chatRoom.setName(chatRoomDTO.getName());
        if (chatRoomDTO.getDescription() != null) chatRoom.setDescription(chatRoomDTO.getDescription());

        repository.save(chatRoom);
        
    }

    public void deleteChatRoom(Long chatRoomId) throws EntityNotFoundException{
        ChatRoom chatRoom = repository.findById(chatRoomId).orElseThrow(() -> new EntityNotFoundException("No chat room found by id: " + chatRoomId));
        repository.delete(chatRoom);
    }

    
    public List<ChatRoomDTO> getAllChatRoomsFromUserId(Long userId) throws EntityNotFoundException{
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("No user found with id: " + userId));
        return chatRoomMembersRepository.findByUser(user).stream().map(x -> x.getChatRoom().getChatId()).map(this::getChatRoomById).toList();
    }

}
