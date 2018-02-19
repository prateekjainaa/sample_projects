package com.pjain.service.hello.rest.resource;

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
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import com.pjain.hello.service.HelloService;
import com.pjain.hello.vo.User;
import com.pjain.service.hello.config.HelloConfiguration;

/**
*
* Just to test. create new resource for project.
*
*/
@Path("/")
@Timed(displayName="topLevel")
public class HelloResource {

    @Context
    private HttpServletRequest servletRequest;

    @Context
    private UriInfo uriInfo;
    
    /*@Inject
    private HelloConfiguration config;*/
    
    @Inject
    @ConfigProperty(name="userName", defaultValue="duke")
    String user;

    @GET
    @Path("/hello")
    public Response helloCall(@QueryParam("name") final String name) {
        return Response.status(200).entity("Hello! " + name).build();
    }
    
    @GET
    @Path("/hello-config")
    public Response helloConfigCall(@QueryParam("name") final String name) {
        return Response.status(200).entity("HEllo!!! " + user).build();
    }
    
    @POST
    @Path("/pojo")
    @Metered(displayName="pojoCall")
    @Timed(displayName="helloTimed")
    @Counted(displayName="helloCounted")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response helloPojoCall(User user) {
        user.setAge(30);
        return Response.status(200).entity(user).build();
    }
}
