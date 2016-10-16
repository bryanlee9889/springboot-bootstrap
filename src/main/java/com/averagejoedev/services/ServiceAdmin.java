package com.averagejoedev.services;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.domains.Admin;
import com.averagejoedev.models.form.Form;

/**
 * Created by voncount on 8/12/16.
 */
public interface ServiceAdmin extends BaseService<Admin> {

    Admin login(Form form) throws ApplicationException;

}
