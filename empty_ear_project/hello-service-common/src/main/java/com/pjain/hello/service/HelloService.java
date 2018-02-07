package com.pjain.hello.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.health.model.HealthService;
import com.pjain.hello.vo.HostAndPort;
import com.pjain.service.hello.config.HelloConfiguration;

public class HelloService {

    @Inject
    private HelloConfiguration config;

    public String greet(String name) {
        return "Hello! " + name;
    }

    public HostAndPort discoverService(String serviceName) {
        ConsulClient client = new ConsulClient(config.getConsulHost());
        Response<List<HealthService>> healthyServices = client.getHealthServices(serviceName, true,
                QueryParams.DEFAULT);
        HostAndPort hp = new HostAndPort();
        for (HealthService hs : healthyServices.getValue()) {
            hp.setPort(hs.getService().getPort());
            hp.setHost(hs.getService().getAddress());
        }
        return hp;
    }

    //@Timeout(2000)
    //@Retry(maxRetries=2)
    //@CircuitBreaker(successThreshold = 2, requestVolumeThreshold = 4, failureRatio=0.75, delay = 1000)
    //@Fallback(fallbackMethod="callHelloService")
    public String callPranamService(final String name) {
        HostAndPort hp = discoverService("pranamService");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://" + hp.getHost() + ":" + hp.getPort() + "/v1/pranamService/root/hello?name="+name);
        javax.ws.rs.core.Response response = target.request().get();
        String value = response.readEntity(String.class);
        response.close(); // You should close connections!
        return value;
    }

    public String callHelloService(final String name) {
        HostAndPort hp = discoverService("helloService");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://" + hp.getHost() + ":" + hp.getPort() + "/v1/helloService/root/hello?name="+name);
        javax.ws.rs.core.Response response = target.request().get();
        String value = response.readEntity(String.class);
        response.close(); // You should close connections!
        return value;
    }
    
}
