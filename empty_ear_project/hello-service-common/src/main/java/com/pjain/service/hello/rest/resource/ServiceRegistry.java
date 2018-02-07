package com.pjain.service.hello.rest.resource;

import java.net.SocketException;
import java.net.UnknownHostException;

import javax.inject.Inject;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import com.pjain.service.hello.config.HelloConfiguration;

public class ServiceRegistry {
    
    @Inject
    private HelloConfiguration config;

    public void register() {
        System.out.println("000000000000000000000000000");
        String host = "localhost";
        try {
            host = NetworkUtil.getAddress();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
        String consul_host = config.getConsulHost();
        ConsulClient client = new ConsulClient(consul_host);
        System.out.println("1111111111111111");
        NewService newService = new NewService();
        newService.setId("pranamService+01");
        newService.setName("pranamService");
        newService.setAddress(host);
        newService.setPort(8080);
        System.out.println("222222222222222");
        NewService.Check serviceCheck = new NewService.Check();
        serviceCheck.setHttp("http://" + host + ":8080/v1/pranamService/root/check");
        serviceCheck.setInterval("3s");
        newService.setCheck(serviceCheck);
        System.out.println("33333333333333");
        client.agentServiceRegister(newService);
        System.out.println("444444444444444");


    }

}
