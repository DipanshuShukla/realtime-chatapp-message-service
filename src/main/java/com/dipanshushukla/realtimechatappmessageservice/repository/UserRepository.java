package com.dipanshushukla.realtimechatappmessageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dipanshushukla.realtimechatappmessageservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
