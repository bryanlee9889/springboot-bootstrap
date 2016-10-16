package com.averagejoedev.services;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.Response;
import com.averagejoedev.models.domains.User;
import com.averagejoedev.models.form.Form;
import com.averagejoedev.models.form.FormUser;

/**
 * Created by voncount on 7/20/16.
 */
public interface ServiceUser extends BaseService<User> {

    /**
     *
     * @param form
     * @param attachment
     * @return
     * @throws ApplicationException
     */
    int create(Form form, Form attachment) throws ApplicationException;

    /**
     *
     * @param form
     * @param attachment
     * @return
     * @throws ApplicationException
     */
    int update(Form form, Form attachment) throws ApplicationException;

    /**
     *
     * @param form
     * @return
     * @throws ApplicationException
     */
    Response login(FormUser form) throws ApplicationException;

    /**
     *
     * @param form
     * @return avatar url
     * @throws ApplicationException
     */
    String uploadAvatar(Form form) throws ApplicationException;
}
