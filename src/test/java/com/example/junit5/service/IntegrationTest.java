package com.example.junit5.service;

import com.example.junit5.model.User;
import com.example.junit5.repository.UserRepository;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class IntegrationTest {
    private static UserRepository userRepository = new UserRepository();


    @Nested
    @Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Get User By Id Tests")
    class DeleteUserTests {

        @Test
        @Order(2)
        @DisplayName("Should delete user from database by id")
        void testDeleteUserById() {
            //given a database with a user with id 1

            //when
            userRepository.deleteUserById(1);

            //then
            assertTrue(userRepository.getUserById(1).isEmpty());
        }

        @Test
        @Order(1)
        @DisplayName("Should throw exception when user not found")
        void testDeleteUserByIdNotFound() {
            //given a database with a user with id 1

            //when
            var e = assertThrows(IllegalArgumentException.class, () -> userRepository.deleteUserById(20));
            //then
            assertNotNull(e);
            assertEquals("User not found", e.getMessage());

        }
    }

    @Nested
    @Order(1)
    @DisplayName("Get User By Id Tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class GetUserByIdTests {

        @Test
        @DisplayName("Should return user by id")
        @Order(1)
        void testGetUserById() {
            //given a database with a user with id 1

            //when
            Optional<User> user = userRepository.getUserById(1);

            //then
            assertTrue(user.isPresent());
            assertEquals(1, user.get().id());
        }

        @Test
        @DisplayName("Should return null when user not found")
        @Order(2)
        void testGetUserByIdNotFound() {
            //given a database with a user with id 1

            //when
            Optional<User> user = userRepository.getUserById(20);

            //then
            assertTrue(user.isEmpty());
        }
    }


}
