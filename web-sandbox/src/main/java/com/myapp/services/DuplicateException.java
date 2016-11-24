package com.myapp.services;

import javax.xml.ws.WebFault;

@WebFault(name = "DuplicateException")
public class DuplicateException extends Exception {

    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
