package com.example.junit5.service;

import com.example.junit5.server.CamundaServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class CamundaTest {
    final CamundaServer camundaServer = CamundaServer.builder().withUrl("localhost").onPort(9090).create();

    @BeforeAll
    void setUpAll() {
        if (!camundaServer.isRunning()) {
            camundaServer.serve();
        }
    }

    @BeforeEach
    void setUp() {
        System.out.println("Resetting WireMock server");
        camundaServer.resetState();
    }

    @AfterAll
    void tearDown() {
        if (camundaServer.isRunning()) {
            System.out.println("Stopping WireMock server");
            camundaServer.shutDown();
        }
    }
}
