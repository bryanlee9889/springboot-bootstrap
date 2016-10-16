package com.averagejoedev.models.enumeration;

/**
 * Created by voncount on 8/31/16.
 */
public enum EnumTokenType {

    SMS_ACTIVATION(0), SMS_RESET_PASSWORD(1);

    private int code;

    EnumTokenType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
