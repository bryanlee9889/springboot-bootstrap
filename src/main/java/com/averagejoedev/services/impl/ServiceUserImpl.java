package com.averagejoedev.services.impl;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.mappers.MapperUser;
import com.averagejoedev.models.Page;
import com.averagejoedev.models.Response;
import com.averagejoedev.models.constant.ConstGlobal;
import com.averagejoedev.models.domains.User;
import com.averagejoedev.models.enumeration.EnumMessage;
import com.averagejoedev.models.enumeration.EnumStatus;
import com.averagejoedev.models.form.Form;
import com.averagejoedev.models.form.FormAttachment;
import com.averagejoedev.models.form.FormUser;
import com.averagejoedev.services.ServiceToken;
import com.averagejoedev.services.ServiceUser;
import com.averagejoedev.utils.*;
import com.averagejoedev.models.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Map;

/**
 * Created by voncount on 7/20/16.
 */
@Service
public class ServiceUserImpl implements ServiceUser {

    public static final Logger logger = LoggerFactory.getLogger(ServiceUserImpl.class);

    private MapperUser mapperUser;
    private ServiceToken serviceToken;

    public ServiceUserImpl(MapperUser mapperUser, ServiceToken serviceToken) {
        this.mapperUser = mapperUser;
        this.serviceToken = serviceToken;
    }

    @Override
    public Response login(FormUser form) throws ApplicationException {
        User user = findOne(form);

        if (user == null) {
            LoggingUtils.loggingDebug(logger, "User not found");
            return new Response<>(EnumMessage.EXCEPTION_BAD_REQUEST, Error.getInstance().rejectField("username", EnumMessage.EXCEPTION_NOT_FOUND.getMessage()));
        }
        else if (user.getStatus() != EnumStatus.ACTIVE.getCode()) {
            LoggingUtils.loggingDebug(logger, "User is locked");
            return new Response<>(EnumMessage.EXCEPTION_BAD_REQUEST, Error.getInstance().rejectField("username", EnumMessage.EXCEPTION_NOT_AVAILABLE.getMessage()));
        }
        else if (!EncryptionUtils.hashPassword(form.getUsername(), form.getPassword()).equals(user.getPassword())) {
            LoggingUtils.loggingDebug(logger, "Password not matched");
            return new Response<>(EnumMessage.EXCEPTION_BAD_REQUEST, Error.getInstance().rejectField("password", EnumMessage.EXCEPTION_FIELD_INVALID.getMessage()));
        }

        Map<String, Object> auth = JsonUtils.convert(user, Map.class);

        auth.put("key", EncryptionUtils.generateToken(user.getId(), user.getUsername()));
        return new Response<>(EnumMessage.OK, auth);
    }

    @Override
    public String uploadAvatar(Form form) throws ApplicationException {
        // insert image
        String relativePath     = "/upload/user/avatar/";
        String UPLOAD_URL       = ConstGlobal.BASE_URL + relativePath;
        String uploadDirectory  = ConstGlobal.UPLOAD_BASE_DIRECTORY + relativePath;

        FormAttachment formAttachment   = (FormAttachment) form;
        Part file                       = formAttachment.getParts().iterator().next();

        // validate content type
        if (!ValidationUtils.File.isValidImage(file.getContentType())) {
            throw new ApplicationException(EnumMessage.EXCEPTION_FILEUPLOAD_CONTENT_TYPE_INVALID);
        }

        String avatarUri;
        try {
            avatarUri = UPLOAD_URL + FileUtils.upload(file.getInputStream(), uploadDirectory, file.getSubmittedFileName());
        } catch (IOException e) {
            throw new ApplicationException(EnumMessage.EXCEPTION_FILEUPLOAD_FAILED);
        }

        // update profile
        FormUser formUser = new FormUser();
        formUser.setId(form.getAuthId());
        formUser.setAvatar(avatarUri);
        mapperUser.update(formUser);
        // return image url
        return avatarUri;
    }

    @Transactional
    @Override
    public int create(Form form, Form attachment) throws ApplicationException {

        FormUser formUser               = (FormUser) form;
        FormAttachment formAttachment   = (FormAttachment) attachment;

        // insert user to DB
        mapperUser.insert(formUser);
        int userID = formUser.getId();

        // update user avatar
        formAttachment.setAuthId(userID);
        this.uploadAvatar(formAttachment);

        return userID;
    }

    @Override
    public int update(Form form, Form attachment) throws ApplicationException {
        FormUser formUser               = (FormUser) form;
        FormAttachment formAttachment   = (FormAttachment) attachment;
        int userID                      = formUser.getId();

        // insert user to DB
        mapperUser.update(formUser);

        // update user avatar
        if (formAttachment.getParts() != null && !formAttachment.getParts().isEmpty()) {
            this.uploadAvatar(formAttachment);
        }

        return userID;
    }

    @Override
    public int create(Form form) throws ApplicationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Form form) throws ApplicationException {
        FormUser formCheckUser = new FormUser();
        formCheckUser.setId(form.getId());

        User user = mapperUser.findOne(formCheckUser);
        if (user == null) {
            LoggingUtils.loggingDebug(logger, "User not found");
            throw new ApplicationException(EnumMessage.EXCEPTION_BAD_REQUEST);
        } else if (!ValidationUtils.Strings.isNullOrEmpty(user.getUsername())) {
            LoggingUtils.loggingDebug(logger, "Username has not been set");
            throw new ApplicationException(EnumMessage.EXCEPTION_BAD_REQUEST);
        }

        return mapperUser.update(form);
    }

    @Override
    public int delete(Form form) throws ApplicationException {
        return mapperUser.delete(form);
    }

    @Override
    public Page<User> filter(Form form) throws ApplicationException {
        return DataUtils.filter(form, mapperUser);
    }

    @Override
    public User findOne(Form form) throws ApplicationException {
        return mapperUser.findOne(form);
    }
}
