package com.example.junit5.service;

import com.example.junit5.server.CamundaServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestServiceThatUsesCamunda extends CamundaTest{

    @Test
    void getsFooFromRemoteServer() {
        System.out.println("Running test");

        // given
        var client = new ServiceThatUsesCamunda();
        camundaServer.whenEndPointCalled("/count-instance").thenRespondWith(100583); //it should mock the http request
        // when
        Integer foo = client.getInstanceCount();
        // then
        assertThat(foo).isEqualTo(100583);
    }
}
