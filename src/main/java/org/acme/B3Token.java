package org.acme;

public class B3Token {

    public final String token_type;

    public final int expires_in;

    public final int ext_expires_in;

    public final String access_token;

    public B3Token(String token_type, int expires_in, int ext_expires_in, String access_token) {
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.ext_expires_in = ext_expires_in;
        this.access_token = access_token;
    }    
    
}
