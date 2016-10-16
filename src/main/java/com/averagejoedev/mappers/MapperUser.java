package com.averagejoedev.mappers;

import com.averagejoedev.models.domains.User;
import com.averagejoedev.models.form.Form;

/**
 * Created by voncount on 7/29/16.
 */
public interface MapperUser extends Mapper<User> {

    int updateProfile(Form form);

}
