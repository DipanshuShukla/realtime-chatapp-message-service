package com.dipanshushukla.realtimechatappmessageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dipanshushukla.realtimechatappmessageservice.entity.ChatRoom;
import com.dipanshushukla.realtimechatappmessageservice.entity.Message;
import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
    List<Message> findByChatRoom(ChatRoom chatRoom);

}
