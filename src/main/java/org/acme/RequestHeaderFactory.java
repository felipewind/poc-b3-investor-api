package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MultivaluedMap;

import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class RequestHeaderFactory implements ClientHeadersFactory {

    private static final Logger LOG = LoggerFactory.getLogger(RequestHeaderFactory.class.getName());

    private static String token;

    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders,
            MultivaluedMap<String, String> clientOutgoingHeaders) {
        LOG.debug("Token=[" + token + "]");
        MultivaluedMap<String, String> result = new MultivaluedMapImpl<>();
        result.add("Authorization", "Bearer " + token);
        return result;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        RequestHeaderFactory.token = token;
    }

}
