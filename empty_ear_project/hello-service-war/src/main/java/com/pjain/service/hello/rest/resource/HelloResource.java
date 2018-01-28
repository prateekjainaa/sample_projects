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

import com.pjain.hello.service.HelloService;
import com.pjain.hello.vo.User;

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
    private HelloService service;

    @GET
    @Path("/hello")
    public Response helloCall(@QueryParam("name") final String name) {
        return Response.status(200).entity("Hello! " + name).build();
    }
    
    @GET
    @Path("/hello-cdi")
    public Response helloCdiCall(@QueryParam("name") final String name) {
        return Response.status(200).entity(service.greet(name)).build();
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
