package com.pjain.service.hello.rest.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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

    @GET
    @Path("/hello")
    public Response helloCall(@QueryParam("name") final String name) {
        return Response.status(200).entity("Hello! " + name).build();
    }
}
