package com.myapp.services;

import javax.xml.ws.WebFault;

/**
 *
 *
 */
@WebFault(name = "DuplicateException")
public class DuplicateException extends Exception {

    /**
     *
     * @param message
     */
    public DuplicateException(final String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public DuplicateException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
