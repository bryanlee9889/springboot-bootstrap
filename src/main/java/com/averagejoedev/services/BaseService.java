package com.averagejoedev.services;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.Page;
import com.averagejoedev.models.domains.Domain;
import com.averagejoedev.models.form.Form;

/**
 * Created by voncount on 8/4/16.
 */
public interface BaseService<T extends Domain> {

    /**
     *
     * @param form
     * @return
     * @throws ApplicationException
     */
    int create(Form form) throws ApplicationException;

    /**
     *
     * @param form
     * @return number indicate method success or failure
     * @throws ApplicationException
     */
    int update(Form form) throws ApplicationException;

    /**
     *
     * @param form
     * @return number indicate method success or failure
     * @throws ApplicationException
     */
    int delete(Form form) throws ApplicationException;

    /**
     *
     * @param form
     * @return pagination of the list of domain object
     * @throws ApplicationException
     */
    Page<T> filter(Form form) throws ApplicationException;

    /**
     *
     * @param form
     * @return lastest domain object that fullfill the criteria
     * @throws ApplicationException
     */
    T findOne(Form form) throws ApplicationException;

}
