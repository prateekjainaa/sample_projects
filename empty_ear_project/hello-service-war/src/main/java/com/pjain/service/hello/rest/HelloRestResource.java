package com.pjain.service.hello.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.pjain.service.hello.rest.resource.HelloResource;

/**
 * Required by resteasy framework.
 *
 */
public class HelloRestResource extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public HelloRestResource() {
        singletons.add(new HelloResource());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}
