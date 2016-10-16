package com.averagejoedev.services.impl;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.mappers.MapperDevice;
import com.averagejoedev.models.Page;
import com.averagejoedev.models.domains.Device;
import com.averagejoedev.models.form.Form;
import com.averagejoedev.services.ServiceDevice;
import com.averagejoedev.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by voncount on 8/31/16.
 */
@Service
public class ServiceDeviceImpl implements ServiceDevice {

    private MapperDevice mapperDevice;

    @Autowired
    public ServiceDeviceImpl(MapperDevice mapperDevice) {
        this.mapperDevice = mapperDevice;
    }

    @Override
    public int create(Form form) throws ApplicationException {
        mapperDevice.insert(form);
        return form.getId();
    }

    @Override
    public int update(Form form) throws ApplicationException {
        return mapperDevice.update(form);
    }

    @Override
    public int delete(Form form) throws ApplicationException {
        return mapperDevice.delete(form);
    }

    @Override
    public Page<Device> filter(Form form) throws ApplicationException {
        return DataUtils.filter(form, mapperDevice);
    }

    @Override
    public Device findOne(Form form) throws ApplicationException {
        return mapperDevice.findOne(form);
    }

}
