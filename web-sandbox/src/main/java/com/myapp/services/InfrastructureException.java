package com.myapp.services;

import javax.xml.ws.WebFault;

/**
 *
 *
 */
@WebFault(name = "InfrastructureException")
public class InfrastructureException extends Exception {

    /**
     *
     * @param message
     */
    public InfrastructureException(final String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public InfrastructureException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
