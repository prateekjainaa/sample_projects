package com.pjain.service.hello.rest.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;

import com.pjain.hello.service.HelloService;
import com.pjain.hello.vo.HostAndPort;
import com.pjain.hello.vo.User;
import com.pjain.service.hello.config.HelloConfiguration;

/**
*
* Just to test. create new resource for project.
*
*/
@Path("/")
public class HelloResource {

    @Context
    private HttpServletRequest servletRequest;

    @Context
    private UriInfo uriInfo;
    
    @Inject
    private ServiceRegistry registry;
    
    @Inject
    @ConfigProperty(name="userName", defaultValue="duke")
    String user;

    @Inject
    private HelloService service;
    
    @GET
    @Path("/register")
    public Response registerToConsul() {
        registry.register();
        return Response.status(200).build();
    }
    
    @GET
    @Path("/check")
    public Response consulCheck() {
        //registry.register();
        return Response.status(200).build();
    }
    
    
    @GET
    @Path("/hello")
    @Fallback(fallbackMethod="fallbackHello")
    public Response helloCall(@QueryParam("name") final String name) {
        return Response.status(200).entity(service.callPranamService(name)).build();
    }
    
    public Response fallbackHello(@QueryParam("name") final String name) {
        return Response.status(200).entity(service.callHelloService(name)).build();
    }
    
    
    @GET
    @Path("/hello-config")
    public Response helloConfigCall(@QueryParam("name") final String name) {
        return Response.status(200).entity("HEllo!!! " + user).build();
    }
    
    @GET
    @Path("/consul-config")
    public Response helloConsulCall(@QueryParam("name") final String name) {
        HostAndPort hp = service.discoverService("pranamService");
        System.out.println("___ address _____" + hp.getHost() + ":"+hp.getPort());
        return Response.status(200).entity(service.callPranamService(name)).build();
    }
    
    
    @POST
    @Path("/pojo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response helloPojoCall(User user) {
        user.setAge(30);
        return Response.status(200).entity(user).build();
    }
}
