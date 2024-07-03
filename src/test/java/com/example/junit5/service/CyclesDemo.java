package com.example.junit5.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CyclesDemo {

    @BeforeAll
    static void initAll() {
        System.out.println("Before all tests");
        // initialize some resources-intensive or time-consuming operations
        // example: create a database connection, read a large file, create a huge variable ...
    }

    @BeforeEach
    void init() {
        System.out.println("Before each test");
        //reset the state of the object under test
    }


    @Test
    void test1() {
        System.out.println("Test 1");
    }

    @Test
    void test2() {
        System.out.println("Test 2");
    }

    @Test
    void test3() {
        System.out.println("Test 3");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each test");
        // clean up the state of the object under test
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("After all tests");
        //close the resources-intensive or time-consuming operations
    }

}
