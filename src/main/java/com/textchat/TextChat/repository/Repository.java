package com.textchat.TextChat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.textchat.TextChat.model.User;

public interface Repository extends MongoRepository<User, String> {
    User findByUsername(String username);
}