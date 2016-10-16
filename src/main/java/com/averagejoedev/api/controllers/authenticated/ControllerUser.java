package com.averagejoedev.api.controllers.authenticated;

import com.averagejoedev.api.controllers.BaseController;
import com.averagejoedev.api.validations.ValidationUser;
import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.Page;
import com.averagejoedev.models.domains.User;
import com.averagejoedev.models.form.FormAttachment;
import com.averagejoedev.models.form.FormUser;
import com.averagejoedev.services.ServiceUser;
import com.averagejoedev.utils.EncryptionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by voncount on 8/9/16.
 */
@SuppressWarnings(value = {"unchecked, rawtype"})
@RestController(value = "apiControllerAuthenticatedUser")
@RequestMapping(value = "/api/v1/auth/users")
public class ControllerUser extends BaseController {

    private ValidationUser validationUser;
    private ServiceUser serviceUser;

    public ControllerUser(ValidationUser validationUser, ServiceUser serviceUser) {
        this.validationUser = validationUser;
        this.serviceUser = serviceUser;
    }

    /**
     * Filter user
     * @param form
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object filter(@ModelAttribute FormUser form) throws ApplicationException {
        Page<User> userPage = serviceUser.filter(form);
        return SUCCESS(userPage);
    }

    /**
     * Get user detail
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(value = "/{id}/profile", method = RequestMethod.GET)
    public Object profile(@ModelAttribute FormUser form) throws ApplicationException {

        User user                   = serviceUser.findOne(form);
        return SUCCESS(user);
    }

    /**
     * Update username and password
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(value = "/profile", method = RequestMethod.PUT)
    public Object update(
            @RequestBody FormUser form
    ) throws ApplicationException {

        form.setId(form.getAuthId());
        form.setPassword(EncryptionUtils.hashPassword(form.getUsername(), form.getPassword()));
        serviceUser.update(form);

        return SUCCESS();
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    public Object uploadAvatar(
            @ModelAttribute FormAttachment form
    ) throws IOException, ServletException, ApplicationException {

        Collection<Part> parts = httpRequest.getParts();
        form.setParts(parts);

        String avatarUri = serviceUser.uploadAvatar(form);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("avatar", avatarUri);

        return SUCCESS(result);
    }


}
