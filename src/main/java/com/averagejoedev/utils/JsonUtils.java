package com.averagejoedev.utils;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.enumeration.EnumMessage;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by voncount on 7/15/16.
 */
public class JsonUtils {

    public static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    public static <T extends Object> T readJson(String jsonString, Class<T> klass) throws ApplicationException {
        try {
            return mapper.readValue(jsonString, klass);
        } catch (IOException e) {
            throw new ApplicationException(EnumMessage.EXCEPTION_PARSE_JSON);
        }
    }

    public static String writeJson(Object object) throws ApplicationException {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ApplicationException(EnumMessage.EXCEPTION_PARSE_JSON);
        }
    }

    public static <T extends Object> T convert(Object source, Class<T> klass) {
        return mapper.convertValue(source, klass);
    }

}
