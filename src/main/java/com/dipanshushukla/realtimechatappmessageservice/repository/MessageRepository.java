package com.dipanshushukla.realtimechatappmessageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dipanshushukla.realtimechatappmessageservice.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

}
