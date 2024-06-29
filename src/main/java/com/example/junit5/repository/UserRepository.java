package com.example.junit5.repository;

import com.example.junit5.model.User;

import java.util.List;

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
}
