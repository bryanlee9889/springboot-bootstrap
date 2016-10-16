package com.averagejoedev.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by voncount on 7/18/16.
 */
@Controller(value = "webControllerHome")
public class ControllerHome {

    @RequestMapping(value = "/")
    public String forwardIndex() {
        return "forward:/docs/api.html";
    }

    @RequestMapping(value = "/agreement")
    public String agreement() {
        return "";
    }

    @RequestMapping(value = "/privacy")
    public String privacy() {
        return "";
    }

    @RequestMapping(value = "/location")
    public String location() {
        return "";
    }

    @RequestMapping(value = "/admin/www/anons/login", method = RequestMethod.GET)
    public String login() {
        return "webs/admin/anons/login";
    }

}
