package com.dipanshushukla.realtimechatappmessageservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipanshushukla.realtimechatappmessageservice.repository.ChatRoomRepository;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository repository;

    


}
