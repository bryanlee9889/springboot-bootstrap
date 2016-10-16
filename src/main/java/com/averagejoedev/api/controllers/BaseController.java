package com.averagejoedev.api.controllers;

import com.averagejoedev.models.Response;
import com.averagejoedev.models.enumeration.EnumMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by voncount on 7/25/16.
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest httpRequest;

    protected ResponseEntity RESPONSE(Object response, HttpStatus status) {
        return new ResponseEntity<>(response, status);
    }

    protected ResponseEntity SUCCESS(Object response) {
        return new ResponseEntity<>(new Response<>(EnumMessage.OK, response), HttpStatus.OK);
    }

    protected ResponseEntity SUCCESS() {
        return new ResponseEntity<>(new Response<>(EnumMessage.OK), HttpStatus.OK);
    }

    protected ResponseEntity FAIL_BAD_REQUEST(Error error) {
        return new ResponseEntity<>(new Response<>(EnumMessage.EXCEPTION_BAD_REQUEST, error), HttpStatus.BAD_REQUEST);
    }

}
