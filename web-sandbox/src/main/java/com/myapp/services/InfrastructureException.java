package com.myapp.services;

import javax.xml.ws.WebFault;

@WebFault(name = "InfrastructureException")
public class InfrastructureException extends Exception {

    public InfrastructureException(String message) {
        super(message);
    }

    public InfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }
}
