package org.acme;

public class HealthCheck {

    public final String status;
    public final String mensagem;

    public HealthCheck(String status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }   
    
}
