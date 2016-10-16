package com.averagejoedev.models;

import com.averagejoedev.models.enumeration.EnumMessage;

import java.io.Serializable;

/**
 * Created by voncount on 7/20/16.
 */
public class Response <T extends Object> implements Serializable {

    private int code;
    private String message;
    private T data;

    public Response() {
    }

    public Response(EnumMessage enumMessage) {
        this.code       = enumMessage.getCode();
        this.message    = enumMessage.getMessage();
    }

    public Response(EnumMessage enumMessage, T data) {
        this.code       = enumMessage.getCode();
        this.message    = enumMessage.getMessage();
        this.data       = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
