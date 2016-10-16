package com.averagejoedev.exceptions;

import com.averagejoedev.models.Response;
import com.averagejoedev.models.enumeration.EnumMessage;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by voncount on 7/20/16.
 */
@ControllerAdvice(basePackages = {
        "com.averagejoedev.api.controllers"
})
public class ExceptionApiHandler {

    public static final Logger logger = Logger.getLogger(ExceptionApiHandler.class);

    @ExceptionHandler(value = ApplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response applicationException(ApplicationException exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        return new Response(exception.getEnumMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Response unauthrorized(Exception exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        return new Response(EnumMessage.EXCEPTION_UNAUTHORIZED);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response exception(Exception exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        return new Response(EnumMessage.EXCEPTION_UNHANDLED);
    }

}
