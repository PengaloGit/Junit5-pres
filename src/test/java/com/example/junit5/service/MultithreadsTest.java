package com.example.junit5.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;



public class MultithreadsTest {

    @Test
    void test() throws InterruptedException {
        Thread.sleep(10000); //10seconds
    }

    @Test
    void test2() throws InterruptedException {
        Thread.sleep(12000); //12seconds
    }

    @Test
    void test3() throws InterruptedException {
        Thread.sleep(3000); //3seconds
    }
}
