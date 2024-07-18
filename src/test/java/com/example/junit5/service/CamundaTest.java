package com.example.junit5.service;

import com.example.junit5.server.CamundaServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class CamundaTest {
     final static CamundaServer camundaServer = CamundaServer.builder().withUrl("localhost").onPort(9090).create();

    @BeforeAll
    static void setUpAll() {
        if (!camundaServer.isRunning()) {
            camundaServer.serve();
        }
    }

    @BeforeEach
    void setUp() {
        System.out.println("Resetting Camunda server");
        camundaServer.resetState();
    }

    @AfterAll
    static void tearDown() {
        if (camundaServer.isRunning()) {
            System.out.println("Stopping Camunda server");
            camundaServer.shutDown();
        }
    }
}
