package com.averagejoedev.models.enumeration;

/**
 * Created by voncount on 7/20/16.
 */
public enum EnumMessage {

    // DONE
    OK(0, "OK"),

    // EXCEPTION
    EXCEPTION_FIELD_INVALID(1, "Field not found or invalid"),
    EXCEPTION_NOT_FOUND(2, "Resource not found"),
    EXCEPTION_NOT_AVAILABLE(3, "Resource not available"),
    EXCEPTION_EXISTED(4, "Resource existed"),

    EXCEPTION_FILEUPLOAD_FAILED(500, "Failed to upload file"),
    EXCEPTION_FILEUPLOAD_CONTENT_TYPE_INVALID(501, "Invalid content type"),

    EXCEPTION_SQL(800, "SQL error"),

    EXCEPTION_HEADER_AUTH_MISSING(994, "Missing header: X-AUTH-TOKEN"),
    EXCEPTION_HEADER_DEVICE_MISSING(995, "Missing header: X-DEVICE"),
    EXCEPTION_UNAUTHORIZED(996, "Unauthorized request"),
    EXCEPTION_BAD_REQUEST(997, "Request params missing or invalid"),
    EXCEPTION_PARSE_JSON(998, "Cannot parse json"),
    EXCEPTION_UNHANDLED(999, "Unhandled exception");

    private int code;
    private String message;

    EnumMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
