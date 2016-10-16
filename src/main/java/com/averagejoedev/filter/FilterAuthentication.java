package com.averagejoedev.filter;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.Response;
import com.averagejoedev.models.enumeration.EnumMessage;
import com.averagejoedev.utils.DateTimeUtils;
import com.averagejoedev.utils.EncryptionUtils;
import com.averagejoedev.utils.JsonUtils;
import com.averagejoedev.utils.ValidationUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by voncount on 8/9/16.
 */
public class FilterAuthentication implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(FilterAuthentication.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest  request     = (HttpServletRequest) servletRequest;
        HttpServletResponse response    = (HttpServletResponse) servletResponse;

        try {
            String authTokenEncrypted   = request.getHeader("X-AUTH-TOKEN");
            if (ValidationUtils.Strings.isNullOrEmpty(authTokenEncrypted)) {
                throw new IllegalArgumentException();
            }
            JSONObject auth             = new JSONObject(EncryptionUtils.decrypt(authTokenEncrypted));

            if (auth.getLong("exp") < DateTimeUtils.now()) {
                // token expired
                throw new BadCredentialsException("Token is expired");
            }

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IllegalArgumentException e) {
            try {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.getWriter().write(JsonUtils.writeJson(new Response(EnumMessage.EXCEPTION_HEADER_AUTH_MISSING)));
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            try {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.getWriter().write(JsonUtils.writeJson(new Response(EnumMessage.EXCEPTION_UNAUTHORIZED)));
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }

}
