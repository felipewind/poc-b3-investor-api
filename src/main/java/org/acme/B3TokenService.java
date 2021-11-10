package org.acme;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface B3TokenService {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public B3Token getB3Token(
        @FormParam("grant_type") String grant_type,
        @FormParam("client_id") String client_id,
        @FormParam("client_secret") String client_secret,
        @FormParam("scope") String scope
    );
    
}
