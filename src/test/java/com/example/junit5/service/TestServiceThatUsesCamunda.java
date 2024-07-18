package com.example.junit5.service;

import com.example.junit5.server.CamundaServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestServiceThatUsesCamunda {

    private static final CamundaServer camundaServer = CamundaServer.builder().withUrl("localhost").onPort(9090).create();

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

    @Test
    void getsFooFromRemoteServer() {
        System.out.println("Running test");

        // given
        var client = new ServiceThatUsesCamunda();
        camundaServer.whenEndPointCalled("/count-instance").thenRespondWith(100583); //it should create a mock
        // when
        Integer foo = client.getInstanceCount();
        // then
        assertThat(foo).isEqualTo(100583);
    }
}
