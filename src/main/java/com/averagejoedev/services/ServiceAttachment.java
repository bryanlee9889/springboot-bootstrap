package com.averagejoedev.services;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.form.Form;

/**
 * Created by voncount on 8/1/16.
 */
public interface ServiceAttachment {

    /**
     *
     * @param form
     * @return avatar url
     * @throws ApplicationException
     */
    String uploadUserAvatar(Form form) throws ApplicationException;

}
