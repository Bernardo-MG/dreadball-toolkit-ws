package com.wandrell.tabletop.dreadball.ws.toolkit.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/version")
public class VersionInfoResource {

    public VersionInfoResource() {
        super();
    }

    @GET
    @Path("/")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response getVersion() {
        final String version;

        version = "test";

        return Response.status(200).entity(version).build();
    }

}
