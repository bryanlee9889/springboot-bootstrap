package com.averagejoedev.api.validations;

import com.averagejoedev.models.Error;
import com.averagejoedev.models.enumeration.EnumMessage;
import com.averagejoedev.models.form.FormUser;
import com.averagejoedev.utils.ValidationUtils;
import org.springframework.stereotype.Component;

/**
 * Created by voncount on 7/20/16.
 */
@Component
public class ValidationUser {

    public Error validateLogin(FormUser form) {
        Error error = new Error();

        String username = form.getUsername();
        String password = form.getPassword();

        if (ValidationUtils.Strings.isNullOrEmpty(username)) {
            error.reject("username", EnumMessage.EXCEPTION_FIELD_INVALID.getMessage());
        }
        if (ValidationUtils.Strings.isNullOrEmpty(password)) {
            error.reject("password", EnumMessage.EXCEPTION_FIELD_INVALID.getMessage());
        }

        return error;
    }

}
