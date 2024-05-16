package com.dipanshushukla.realtimechatappmessageservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipanshushukla.realtimechatappmessageservice.entity.ChatRoom;
import com.dipanshushukla.realtimechatappmessageservice.entity.ChatRoomMembers;
import com.dipanshushukla.realtimechatappmessageservice.entity.User;
import com.dipanshushukla.realtimechatappmessageservice.model.ChatRoomMembersId;
import com.dipanshushukla.realtimechatappmessageservice.repository.ChatRoomMembersRepository;
import com.dipanshushukla.realtimechatappmessageservice.repository.ChatRoomRepository;
import com.dipanshushukla.realtimechatappmessageservice.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ChatRoomMembersService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomMembersRepository chatRoomMembersRepository;

    public void addMember(Long chatId, Long userId){
        // Check if chat room and user exists
        ChatRoom chatRoom = chatRoomRepository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Chat Room not found with id: " + chatId ));
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found with id: " + userId ));

        // check if user already a member of chat room
        if (chatRoomMembersRepository.existsByChatRoomAndUser(chatRoom, user)){
            throw new IllegalArgumentException("User is already a member of the chat room.");
        }

        ChatRoomMembersId chatRoomMembersId = new ChatRoomMembersId(chatId, userId);
        ChatRoomMembers chatRoomMembers = new ChatRoomMembers(chatRoomMembersId, chatRoom, user);

        chatRoomMembersRepository.save(chatRoomMembers);

    }

    public void removeMember(Long chatId, Long userId){
        
        // Check if chat room and user exists
        ChatRoom chatRoom = chatRoomRepository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Chat Room not found with id: " + chatId ));
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found with id: " + userId ));
        
        ChatRoomMembersId chatRoomMembersId = new ChatRoomMembersId(chatId, userId);
        chatRoomMembersRepository.deleteById(chatRoomMembersId);
    }

    public List<Long> getMembers(Long chatId){
        // check if chat room exits
        ChatRoom chatRoom = chatRoomRepository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Chat Room not found with id: " + chatId ));
        return chatRoomMembersRepository.findByChatRoom(chatRoom).stream().map((x) -> x.getUser().getUserId()).toList();
    }

}
