package com.averagejoedev.models.enumeration;

/**
 * Created by voncount on 7/20/16.
 */
public enum EnumStatus {

    PENDING(0), ACTIVE(1), SUSPENDED(2), BLOCKED(3), DELETED(9);

    private int code;

    EnumStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
