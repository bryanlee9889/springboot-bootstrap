package com.averagejoedev.utils;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.domains.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.Map;

/**
 * Created by voncount on 9/6/16.
 */
public class ServletUtils {

    @SuppressWarnings({"unchecked"})
    public static Map<String, Object> getAuth(HttpServletRequest request) throws ApplicationException {
        String authTokenEncrypted   = request.getHeader("X-AUTH-TOKEN");
        return JsonUtils.readJson(EncryptionUtils.decrypt(authTokenEncrypted), Map.class);
    }

    public static String[] getMobileLogging(HttpServletRequest request) {
        String mobileLogging        = request.getHeader("X-DEVICE");
        return new String(Base64.getDecoder().decode(mobileLogging)).split(";");
    }

    public static void setAdminSession(HttpServletRequest request, Admin admin) {
        HttpSession session = request.getSession(true);
        session.setAttribute("admin", admin);
    }

    public static Admin getAdminSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Admin) session.getAttribute("admin");
    }
}
