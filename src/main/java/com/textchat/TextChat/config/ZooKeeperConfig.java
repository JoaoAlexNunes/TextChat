package com.textchat.TextChat.config;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ZooKeeperConfig {

    @Bean
    public ZooKeeper zooKeeper() throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 3000, null);
        return zooKeeper;
    }
}