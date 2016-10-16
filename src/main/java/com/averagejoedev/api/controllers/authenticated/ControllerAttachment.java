package com.averagejoedev.api.controllers.authenticated;

import com.averagejoedev.api.controllers.BaseController;
import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.form.FormAttachment;
import com.averagejoedev.services.ServiceAttachment;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
/**
 * Created by voncount on 8/1/16.
 */
@SuppressWarnings(value = {"unchecked, rawtype"})
@RestController(value = "apiAuthenticatedControllerAttachment")
@RequestMapping(value = "/api/v1/auth/attachments")
public class ControllerAttachment extends BaseController {

    private ServiceAttachment serviceAttachment;

    public ControllerAttachment(ServiceAttachment serviceAttachment) {
        this.serviceAttachment = serviceAttachment;
    }

    @RequestMapping(value = "/users/avatar", method = RequestMethod.POST)
    public Object uploadUserAvatar(
            @ModelAttribute FormAttachment form
    ) throws ApplicationException, IOException, ServletException {

        Collection<Part> parts = httpRequest.getParts();
        form.setParts(parts);

        String avatarUrl = serviceAttachment.uploadUserAvatar(form);
        return SUCCESS(Collections.singletonMap("avatar", avatarUrl));
    }

}
