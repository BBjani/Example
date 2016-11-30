package com.myapp.services;

import javax.xml.ws.WebFault;

/**
 *
 *
 */
@WebFault(name = "ValidationException")
public class ValidationException extends Exception {

    private final ValidationExceptionBean faultBean;

    /**
     *
     * @param message
     * @param faultInfo
     */
    public ValidationException(final String message, final ValidationExceptionBean faultInfo) {
        super(message);
        faultBean = faultInfo;
    }

    /**
     *
     * @param message
     * @param faultInfo
     * @param cause
     */
    public ValidationException(final String message, final ValidationExceptionBean faultInfo, final Throwable cause) {
        super(message, cause);
        faultBean = faultInfo;
    }

    /**
     *
     * @return
     */
    public ValidationExceptionBean getFault() {
        return this.faultBean;
    }
}
