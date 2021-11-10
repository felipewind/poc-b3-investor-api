package org.acme;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
@Path("api/healthcheck")
public class B3ApiResource {

    private final Logger LOG = LoggerFactory.getLogger(B3ApiResource.class);

    @Inject
    @RestClient
    B3ApiService b3ApiService;

    @Inject
    ObjectMapper mapper;

    @GET
    @Path("{token}")
    public Response get(@PathParam("token") String token) throws JsonProcessingException {

        LOG.debug("get() token = " + token);

        RequestHeaderFactory.setToken(token);

        var healthCheck = b3ApiService.getHealthCheck();
        
        LOG.debug("healthCheck = " + mapper.writeValueAsString(healthCheck));

        return Response.status(Status.OK).entity(healthCheck).build();

    }
    
}
