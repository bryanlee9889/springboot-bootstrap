package com.averagejoedev.exceptions;

import com.averagejoedev.models.enumeration.EnumMessage;

/**
 * Created by voncount on 7/20/16.
 */
public class ApplicationException extends Exception {

    private EnumMessage enumMessage;

    public ApplicationException(EnumMessage enumMessage) {
        this.enumMessage = enumMessage;
    }

    public ApplicationException(String message, EnumMessage enumMessage) {
        super(message);
        this.enumMessage = enumMessage;
    }

    public ApplicationException(String message, Throwable cause, EnumMessage enumMessage) {
        super(message, cause);
        this.enumMessage = enumMessage;
    }

    public ApplicationException(Throwable cause, EnumMessage enumMessage) {
        super(cause);
        this.enumMessage = enumMessage;
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, EnumMessage enumMessage) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.enumMessage = enumMessage;
    }

    public EnumMessage getEnumMessage() {
        return enumMessage;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
