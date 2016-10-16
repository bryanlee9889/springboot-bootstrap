package com.averagejoedev.filter;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.domains.Admin;
import com.averagejoedev.models.enumeration.EnumMessage;
import com.averagejoedev.utils.ServletUtils;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by voncount on 9/29/16.
 */
public class FilterAdmin implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request     = (HttpServletRequest) servletRequest;
        HttpServletResponse response    = (HttpServletResponse) servletResponse;

        try {
            Admin admin = ServletUtils.getAdminSession(request);
            if (admin == null) {
                throw new ApplicationException(EnumMessage.EXCEPTION_UNAUTHORIZED);
            }

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void destroy() {

    }
}
