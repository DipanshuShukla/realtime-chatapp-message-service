package com.dipanshushukla.realtimechatappmessageservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipanshushukla.realtimechatappmessageservice.dto.MessageDTO;
import com.dipanshushukla.realtimechatappmessageservice.entity.ChatRoom;
import com.dipanshushukla.realtimechatappmessageservice.entity.Message;
import com.dipanshushukla.realtimechatappmessageservice.entity.User;
import com.dipanshushukla.realtimechatappmessageservice.model.MessageStatus;
import com.dipanshushukla.realtimechatappmessageservice.repository.ChatRoomRepository;
import com.dipanshushukla.realtimechatappmessageservice.repository.MessageRepository;
import com.dipanshushukla.realtimechatappmessageservice.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public void createMessage(MessageDTO messageDto) throws EntityNotFoundException{

        User user = userRepository.findById(messageDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("No user found with id: " + messageDto.getUserId()));
        ChatRoom chatRoom = chatRoomRepository.findById(messageDto.getChatRoomId()).orElseThrow(() -> new EntityNotFoundException("No chat room found with id: " + messageDto.getChatRoomId()));

        Message message = new Message();
        message.setChatRoom(chatRoom);
        message.setUser(user);
        message.setContent(messageDto.getContent());
        if (messageDto.getType() != null) message.setType(messageDto.getType());

        messageRepository.save(message);
    }

    public List<MessageDTO> getMessagesFromChatRoom(Long chatRoomId) throws EntityNotFoundException{
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new EntityNotFoundException("No chat room foud with id: " + chatRoomId));
        return messageRepository.findByChatRoom(chatRoom).stream().map(MessageDTO::fromEntity).toList();
    }

    public MessageDTO getMessageFromMessageId(Long messageId) throws EntityNotFoundException{
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new EntityNotFoundException("No message found with id: " + messageId));
        return MessageDTO.fromEntity(message);
    }

    public void updateMessageStatus(Long messageId) throws EntityNotFoundException{
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new EntityNotFoundException("No message found with id: " + messageId));
        message.setStatus(MessageStatus.READ);
        messageRepository.save(message);
    }
}
