package com.dipanshushukla.realtimechatappmessageservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dipanshushukla.realtimechatappmessageservice.entity.ChatRoom;
import com.dipanshushukla.realtimechatappmessageservice.entity.ChatRoomMembers;
import com.dipanshushukla.realtimechatappmessageservice.entity.User;
import com.dipanshushukla.realtimechatappmessageservice.model.ChatRoomMembersId;

@Repository
public interface ChatRoomMembersRepository extends JpaRepository<ChatRoomMembers,ChatRoomMembersId>{
    
    boolean existsByChatRoomAndUser(ChatRoom chatRoom, User user);

    List<ChatRoomMembers> findByChatRoom(ChatRoom chatRoom);
    List<ChatRoomMembers> findByUser(User user);
}
