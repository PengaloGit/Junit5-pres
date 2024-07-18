package com.example.junit5.server;

import lombok.Setter;

@Setter
public class CamundaServer {

    private Integer port;

    private String url;

    private boolean started = false;

    public void serve() {
        started = true;
        System.out.println("Camunda server is up and running !!");
    }

    public void shutDown() {
        started = false;
        System.out.println("Camunda server is shutting down :( ");
    }

    public boolean isRunning() {
        return started;
    }

    public void resetState(){
        shutDown();
        serve();

    }

    public X whenEndPointCalled(String endPoint){
        return ()-> {};
    }


    public static class MyClassBuilder {
        private String url;
        private Integer port;

        public MyClassBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public MyClassBuilder onPort(Integer port) {
            this.port = port;
            return this;
        }


        public CamundaServer create() {
            CamundaServer camundaServer = new CamundaServer();
            camundaServer.setUrl(this.url);
            camundaServer.setPort(this.port);
            return camundaServer;
        }
    }

    public static MyClassBuilder builder() {
        return new MyClassBuilder();
    }
    public interface X {
        default void thenRespondWith(Object responseBody){
        }

        void apply();
    }
}

