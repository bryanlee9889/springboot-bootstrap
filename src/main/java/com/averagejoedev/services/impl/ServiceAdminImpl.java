package com.averagejoedev.services.impl;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.mappers.MapperAdmin;
import com.averagejoedev.models.Page;
import com.averagejoedev.models.domains.Admin;
import com.averagejoedev.models.enumeration.EnumMessage;
import com.averagejoedev.models.form.Form;
import com.averagejoedev.models.form.FormAdmin;
import com.averagejoedev.services.ServiceAdmin;
import com.averagejoedev.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by voncount on 8/12/16.
 */
@Service
public class ServiceAdminImpl implements ServiceAdmin {

    private MapperAdmin mapperAdmin;

    @Autowired
    public ServiceAdminImpl(MapperAdmin mapperAdmin) {
        this.mapperAdmin = mapperAdmin;
    }


    @Override
    public int create(Form form) throws ApplicationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Form form) throws ApplicationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(Form form) throws ApplicationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<Admin> filter(Form form) throws ApplicationException {
        return null;
    }

    @Override
    public Admin findOne(Form form) throws ApplicationException {
        return mapperAdmin.findOne(form);
    }

    @Override
    public Admin login(Form form) throws ApplicationException {
        FormAdmin formAdmin = (FormAdmin) form;
        Admin admin = mapperAdmin.findOne(formAdmin);
        if (admin == null) {
            throw new ApplicationException(EnumMessage.EXCEPTION_NOT_FOUND);
        } else {
            if (!admin.getPassword().equals(EncryptionUtils.hashPassword(formAdmin.getUsername(), formAdmin.getPassword()))) {
                throw new ApplicationException(EnumMessage.EXCEPTION_BAD_REQUEST);
            }
            return admin;
        }
    }
}
