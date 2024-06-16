package com.textchat.TextChat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

    @Document
    public class User {
        @Id
        private String userId;
        private String username;
        private String password;


        public User(String username, String password) {
            super();
            this.username = username;
            this.password = password;
        }


        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

    }
