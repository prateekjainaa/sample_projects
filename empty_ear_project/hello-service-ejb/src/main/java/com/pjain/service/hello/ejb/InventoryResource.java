package com.pjain.service.hello.ejb;

import javax.ejb.Stateless;

@Stateless
public class InventoryResource {
    
    public String greet(String name) {
        return "Hello! " + name;
    }

}
