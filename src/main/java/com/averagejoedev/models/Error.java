package com.averagejoedev.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by voncount on 7/20/16.
 */
public class Error implements Serializable {

    private Map<String, String> errors = new HashMap<>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public void reject(String key, String value) {
        errors.put(key, value);
    }

    public boolean hasError() {
        return !errors.isEmpty();
    }

    public static Error getInstance() {
        return new Error();
    }

    public Error rejectField(String key, String value) {
        this.errors.put(key, value);
        return this;
    }

}
