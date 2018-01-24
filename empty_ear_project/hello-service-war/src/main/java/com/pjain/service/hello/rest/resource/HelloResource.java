package com.pjain.service.hello.rest.resource;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.pjain.service.hello.ejb.InventoryResource;

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
    
    @EJB
    private InventoryResource inventoryResource;

    @GET
    @Path("/hello")
    public Response helloCall(@QueryParam("name") final String name) {
        return Response.status(200).entity("Hello! " + name).build();
    }
    
    @GET
    @Path("/cdi")
    //@Produces(MediaType.APPLICATION_JSON)
    public Response cdiCall(@QueryParam("name") final String name) {
        return Response.status(200).entity(inventoryResource.greet(name)).build();
    } 
    
}
