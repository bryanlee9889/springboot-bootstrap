package com.averagejoedev.services.impl;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.mappers.MapperUser;
import com.averagejoedev.models.constant.ConstGlobal;
import com.averagejoedev.models.enumeration.EnumMessage;
import com.averagejoedev.models.form.Form;
import com.averagejoedev.models.form.FormAttachment;
import com.averagejoedev.models.form.FormUser;
import com.averagejoedev.services.ServiceAttachment;
import com.averagejoedev.utils.FileUtils;
import com.averagejoedev.utils.ValidationUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Created by voncount on 8/1/16.
 */
@Service
public class ServiceAttachmentImpl implements ServiceAttachment {

    public static final Logger logger = Logger.getLogger(ServiceAttachmentImpl.class);

    private MapperUser mapperUser;

    public ServiceAttachmentImpl(MapperUser mapperUser) {
        this.mapperUser = mapperUser;
    }

    @Override
    public String uploadUserAvatar(Form form) throws ApplicationException {
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

            // update profile
            FormUser formUser = new FormUser();
            formUser.setId(form.getAuthId());
            formUser.setAvatar(avatarUri);
            mapperUser.update(formUser);
            // return image url
            return avatarUri;
        } catch (IOException e) {
            throw new ApplicationException(EnumMessage.EXCEPTION_FILEUPLOAD_FAILED);
        }
    }

}
