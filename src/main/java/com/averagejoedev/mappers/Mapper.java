package com.averagejoedev.mappers;


import com.averagejoedev.models.domains.Domain;
import com.averagejoedev.models.form.Form;

import java.util.List;

/**
 * Created by voncount on 8/4/16.
 */
public interface Mapper<T extends Domain> {

    List<T> find(Form form);

    int count(Form form);

    T findOne(Form form);

    int insert(Form form);

    int update(Form form);

    int delete(Form form);
}
