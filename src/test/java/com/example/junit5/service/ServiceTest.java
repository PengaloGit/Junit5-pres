package com.example.junit5.service;

import com.example.junit5.annotation.IgnoreOnLocalEnv;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class ServiceTest {

    @Test
    @DisplayName("Should finish execution in less than 3 seconds")
    @IgnoreOnLocalEnv
    void algoPerformanceTest() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Test finished");
        // I don't want this test to depend on the devs' machine specs
        // I want this test to only be executed when in the CI/CD pipeline
    }
    
    @Test
    @DisplayName("Should exceed timeout of 10 ms and wait for task completion")
    void testTimeoutExceeded() {
        assertTimeout(ofMillis(10), () -> {
            Thread.sleep(100);
        });
    }

    @Test
    @DisplayName("Should exceed timeout of 10 ms and prematurely terminate task")
    void timeoutExceededWithPreemptiveTermination() {
        assertTimeoutPreemptively(ofMillis(10), () -> {
            Thread.sleep(100);
        });
    }
}
