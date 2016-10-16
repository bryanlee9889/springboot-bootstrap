package com.averagejoedev.api.controllers.admin;

import com.averagejoedev.api.controllers.BaseController;
import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.Page;
import com.averagejoedev.models.domains.User;
import com.averagejoedev.models.enumeration.EnumStatus;
import com.averagejoedev.models.form.FormAttachment;
import com.averagejoedev.models.form.FormUser;
import com.averagejoedev.services.ServiceUser;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by voncount on 10/10/16.
 */
@RestController(value = "apiControllersAdminUser")
@RequestMapping(value = "/admin/api/auth/users")
public class ControllerUser extends BaseController {

    private ServiceUser serviceUser;

    public ControllerUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public Object init(
            @ModelAttribute FormUser form
    ) {
        return SUCCESS();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object filter(
            @ModelAttribute FormUser form
    ) throws ApplicationException {

        Page<User> filter = serviceUser.filter(form);
        return SUCCESS(filter);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(
            @ModelAttribute FormUser form
    ) throws ApplicationException, IOException, ServletException {

        FormAttachment attachment = new FormAttachment();
        attachment.setParts(httpRequest.getParts());

        form.setStatus(EnumStatus.ACTIVE.getCode());
        int id = serviceUser.create(form, attachment);
        return SUCCESS(Collections.singletonMap("id", id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object detail(
            @ModelAttribute FormUser form
    ) throws ApplicationException {

        User user = serviceUser.findOne(form);
        return SUCCESS(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(
            @ModelAttribute FormUser form
    ) throws ApplicationException, IOException, ServletException {

        FormAttachment attachment = new FormAttachment();
        attachment.setAuthId(form.getId());
        attachment.setParts(httpRequest.getParts());

        serviceUser.update(form, attachment);
        return SUCCESS();
    }

}
