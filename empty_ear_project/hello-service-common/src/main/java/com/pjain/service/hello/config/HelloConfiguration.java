package com.pjain.service.hello.config;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class HelloConfiguration {

    @Inject
    @ConfigProperty(name="user", defaultValue="Duke")
    String user;
    
    @Inject
    @ConfigProperty(name="consul.host", defaultValue="192.168.1.10")
    String consulHost;
    
    @Inject
    @ConfigProperty(name="consul.port", defaultValue="8080")
    String consulPort;
    
    @Inject
    @ConfigProperty(name="service.name", defaultValue="default-service")
    String serviceName;
    
    public String getServiceName() {
        return serviceName;
    }

    public String getConsulHost() {
        return consulHost;
    }

    public String getConsulPort() {
        return consulPort;
    }

    public String getUser() {
        return user;
    }    
}
