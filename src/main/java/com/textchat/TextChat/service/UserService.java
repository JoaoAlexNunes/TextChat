package com.textchat.TextChat.service;

import com.textchat.TextChat.model.User;
import com.textchat.TextChat.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.ZooKeeper;

@Service
public class UserService {

    @Autowired
    private Repository userRepository;
    @Autowired
    private ZooKeeper zooKeeper;

    public UserService(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        this.zooKeeper = zooKeeper;
        // Create the parent node /users if it doesn't exist
        Stat stat = zooKeeper.exists("/users", false);
        if (stat == null) {
            zooKeeper.create("/users", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        // Add a watcher to the /users node
        zooKeeper.exists("/users", null);
    }


    public void saveUserToZooKeeper(User user) throws KeeperException, InterruptedException {
        try{
            // Create the parent node /users if it doesn't exist
            String path = "/users/" + user.getUsername();
            Stat stat = zooKeeper.exists(path, false);
            zooKeeper.create(path, user.toString().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            userRepository.save(user);

        }catch (KeeperException | InterruptedException e) {
            // handle ZooKeeper errors
            throw new RuntimeException(e);
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}