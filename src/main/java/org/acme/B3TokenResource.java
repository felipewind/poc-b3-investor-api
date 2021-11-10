package org.acme;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/token")
public class B3TokenResource {

    private final Logger LOG = LoggerFactory.getLogger(B3TokenResource.class);

    @Inject
    ObjectMapper mapper;

    @Inject
    @RestClient
    B3TokenService b3TokenService;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(
        // @NotNull @FormParam("grant_type") String grant_type,
        @NotNull @FormParam("client_id") String client_id,
        @NotNull @FormParam("client_secret") String client_secret
        // ,@NotNull @FormParam("scope") String scope
        ) throws JsonProcessingException {

        // LOG.debug("hello() + grant_type=" + grant_type + " client_id=" +
        //           client_id + " client_secret=" + client_secret + 
        //           " scope=" + scope);

        LOG.debug("hello() client_id=" + client_id + " client_secret=" + client_secret);

        String grant_type = "client_credentials";
        String scope = "0c991613-4c90-454d-8685-d466a47669cb/.default";
        
        var b3Token = b3TokenService.getB3Token(grant_type, client_id, client_secret, scope);

        LOG.debug(mapper.writeValueAsString(b3Token));

        return Response.status(Status.OK).entity(b3Token).build();

    }
}
