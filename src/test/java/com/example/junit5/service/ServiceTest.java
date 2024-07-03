package com.example.junit5.service;

import com.example.junit5.annotation.IgnoreOnLocalEnv;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServiceTest {

    @Test
    @DisplayName("Should finish execution in less than 3 seconds")
    @IgnoreOnLocalEnv
    void algoPerformanceTest() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Test finished");
        // I don't want this test to depend on the devs machine specs
        // I want this test to on ly be excuted on the CI/CD pipeline
    }
}
