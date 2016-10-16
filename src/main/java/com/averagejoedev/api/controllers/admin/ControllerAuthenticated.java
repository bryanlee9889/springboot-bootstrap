package com.averagejoedev.api.controllers.admin;

import com.averagejoedev.api.controllers.BaseController;
import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.domains.Admin;
import com.averagejoedev.models.form.FormAdmin;
import com.averagejoedev.services.ServiceAdmin;
import com.averagejoedev.utils.ServletUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by voncount on 9/29/16.
 */
@SuppressWarnings(value = {"unchecked, rawtype"})
@RestController(value = "apiControllersAminAuthenticated")
@RequestMapping(value = "/admin/api/anons")
public class ControllerAuthenticated extends BaseController {

    private ServiceAdmin serviceAdmin;

    public ControllerAuthenticated(ServiceAdmin serviceAdmin) {
        this.serviceAdmin = serviceAdmin;
    }

    @RequestMapping(value = "/login")
    public Object login(@RequestBody FormAdmin form) throws ApplicationException {

        Admin admin = serviceAdmin.login(form);
        ServletUtils.setAdminSession(httpRequest, admin);

        return SUCCESS();
    }

}
