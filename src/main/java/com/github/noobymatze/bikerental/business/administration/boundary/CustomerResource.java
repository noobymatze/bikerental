package com.github.noobymatze.bikerental.business.administration.boundary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource for customers.
 *
 * @author Matthias Metzger
 */
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    Customers customers;

    @GET
    public Response getAll() {
        return Response.ok(customers.getAll()).build();
    }

}
