package com.dipanshushukla.realtimechatappmessageservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipanshushukla.realtimechatappmessageservice.dto.ChatRoomMembersDTO;
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

    public void addMember(ChatRoomMembersDTO chatRoomMembersDTO) throws EntityNotFoundException, IllegalArgumentException{
        // Check if chat room and user exists
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomMembersDTO.getChatId()).orElseThrow(() -> new EntityNotFoundException("Chat Room not found with id: " + chatRoomMembersDTO.getChatId()));
        User user = userRepository.findById(chatRoomMembersDTO.getUserId()).orElseThrow(()-> new EntityNotFoundException("User not found with id: " + chatRoomMembersDTO.getUserId()));

        // check if user already a member of chat room
        if (chatRoomMembersRepository.existsByChatRoomAndUser(chatRoom, user)){
            throw new IllegalArgumentException("User is already a member of the chat room.");
        }

        ChatRoomMembersId chatRoomMembersId = new ChatRoomMembersId(chatRoomMembersDTO.getChatId(), chatRoomMembersDTO.getUserId());
        ChatRoomMembers chatRoomMembers = new ChatRoomMembers(chatRoomMembersId, chatRoom, user);

        chatRoomMembersRepository.save(chatRoomMembers);

    }

    public void removeMember(Long chatRoomId, Long userId) throws EntityNotFoundException{
        
        // Check if chat room and user exists
        chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new EntityNotFoundException("Chat Room not found with id: " + chatRoomId ));
        userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found with id: " + userId ));
        
        ChatRoomMembersId chatRoomMembersId = new ChatRoomMembersId(chatRoomId, userId);
        chatRoomMembersRepository.deleteById(chatRoomMembersId);
    }

    public List<Long> getMembers(Long chatRoomId) throws EntityNotFoundException{
        // check if chat room exits
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new EntityNotFoundException("Chat Room not found with id: " + chatRoomId ));
        return chatRoomMembersRepository.findByChatRoom(chatRoom).stream().map((x) -> x.getUser().getUserId()).toList();
    }

}
