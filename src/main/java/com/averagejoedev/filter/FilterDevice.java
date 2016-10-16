package com.averagejoedev.filter;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.Response;
import com.averagejoedev.models.enumeration.EnumMessage;
import com.averagejoedev.utils.JsonUtils;
import com.averagejoedev.utils.ValidationUtils;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by voncount on 9/27/16.
 */
public class FilterDevice implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request      = (HttpServletRequest) servletRequest;
        HttpServletResponse response    = (HttpServletResponse) servletResponse;

        try {
            String deviceToken   = request.getHeader("X-DEVICE");
            if (ValidationUtils.Strings.isNullOrEmpty(deviceToken)) {
                throw new IllegalArgumentException();
            }

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            try {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.getWriter().write(JsonUtils.writeJson(new Response(EnumMessage.EXCEPTION_HEADER_DEVICE_MISSING)));
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }

}
