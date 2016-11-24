package com.myapp.services;

import javax.xml.ws.WebFault;

@WebFault(name = "ValidationException")
public class ValidationException extends Exception {

    private final ValidationExceptionBean faultBean;

    public ValidationException(String message, ValidationExceptionBean faultInfo) {
        super(message);
        faultBean = faultInfo;
    }
    
    public ValidationException(String message, ValidationExceptionBean faultInfo, Throwable cause) {
        super(message, cause);
        faultBean = faultInfo;
    }

    public ValidationExceptionBean getFault() {
        return this.faultBean;
    }
}
