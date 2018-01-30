package com.pjain.service.hello.config;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class HelloConfiguration {

    @Inject
    @ConfigProperty(name="user", defaultValue="Duke")
    String user;
    
    public String getUser() {
        return user;
    }
    
    
}
