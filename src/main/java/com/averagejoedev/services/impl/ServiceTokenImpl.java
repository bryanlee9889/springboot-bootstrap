package com.averagejoedev.services.impl;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.mappers.MapperToken;
import com.averagejoedev.models.Page;
import com.averagejoedev.models.domains.Token;
import com.averagejoedev.models.form.Form;
import com.averagejoedev.models.form.FormToken;
import com.averagejoedev.services.ServiceToken;
import com.averagejoedev.utils.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by voncount on 8/31/16.
 */
@Service
public class ServiceTokenImpl implements ServiceToken {

    private static final Logger logger = LoggerFactory.getLogger(ServiceTokenImpl.class);

    private MapperToken mapperToken;

    @Autowired
    public ServiceTokenImpl(MapperToken mapperToken) {
        this.mapperToken = mapperToken;
    }

    @Override
    public int create(Form form) throws ApplicationException {
        FormToken formToken = (FormToken) form;
        mapperToken.insert(formToken);
        return formToken.getId();
    }

    @Override
    public int update(Form form) throws ApplicationException {
        return mapperToken.update(form);
    }

    @Override
    public int delete(Form form) throws ApplicationException {
        return mapperToken.delete(form);
    }

    @Override
    public Page<Token> filter(Form form) throws ApplicationException {
        return DataUtils.filter(form, mapperToken);
    }

    @Override
    public Token findOne(Form form) throws ApplicationException {
        return mapperToken.findOne(form);
    }

}
