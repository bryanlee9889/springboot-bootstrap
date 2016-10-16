package com.averagejoedev.services.securities;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by voncount on 9/21/16.
 */
@Component
public class MethodSecurityService {

    @Autowired
    private HttpServletRequest httpRequest;

}
