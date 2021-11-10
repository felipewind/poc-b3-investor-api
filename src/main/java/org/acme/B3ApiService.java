package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@RegisterClientHeaders(RequestHeaderFactory.class)
public interface B3ApiService {

    @GET
    @Path("/acesso/healthcheck")
    public HealthCheck getHealthCheck();
    
}

