package com.example.junit5.repository;

import com.example.junit5.model.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserRepository {
    List<User> users = List.of(new User(1, "ahmed@example.com", true),
            new User(2, "mohamed@example.com", false),
            new User(3, "fatima@example.com", true),
            new User(4, "khalid@example.com", false),
            new User(5, "yasmin@example.com", true),
            new User(6, "omar@example.com", false),
            new User(7, "layla@example.com", true),
            new User(8, "saeed@example.com", false),
            new User(9, "hana@example.com", true),
            new User(10, "ali@example.com", false));

    public Optional<User> getUserById(int userId) {
        return users.stream().filter(user -> user.id() == userId).findFirst();
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void deleteUserById(Integer id) {
        if(getUserById(id).isEmpty()){
            throw new IllegalArgumentException("User not found");
        }
        users = users.stream().filter(user -> !Objects.equals(user.id(), id)).toList();
    }
}
