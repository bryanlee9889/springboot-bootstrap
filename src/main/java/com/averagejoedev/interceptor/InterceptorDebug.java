package com.averagejoedev.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Created by voncount on 7/25/16.
 */
@Component
public class InterceptorDebug extends HandlerInterceptorAdapter {

    public static final Logger logger = LoggerFactory.getLogger(InterceptorDebug.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            if (logger.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder("\n");
                sb.append("===================================================================================================").append("\n")
                        .append("Request Info:").append("\n")
                        .append("URL            : " + request.getRequestURL().toString()).append("\n")
                        .append("URI            : " + request.getRequestURI()).append("\n")
                        .append("Referer        : " + request.getHeader("referer")).append("\n")
                        .append("Method         : " + request.getMethod()).append("\n")
                        .append("IP             : " + request.getRemoteAddr()).append("\n")
                        .append("User Agent     : " + request.getHeader("User-Agent")).append("\n")
                        .append("Params         : ").append("\n");

                Enumeration<String> parameterNames = request.getParameterNames();
                String key;
                while (parameterNames.hasMoreElements()) {
                    key = parameterNames.nextElement();
                    sb
                            .append(key).append("\t").append("=").append("\t")
                            .append(request.getParameter(key)).append("\n");
                }

                sb.append("Files:").append("\n");
                if (request.getHeader("Content-Type").startsWith("multipart/form-data") && request.getParts() != null) {
                    request.getParts().forEach(p ->
                        sb.append(p.getSubmittedFileName()).append(" | ").append(p.getContentType()).append(" | ").append(p.getSize()).append("\n")
                    );
                }

                sb.append("===================================================================================================").append("\n");
                logger.debug(sb.toString());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        try {
            if (logger.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder("\n");
                sb.append("===================================================================================================").append("\n")
                        .append("Response Info:").append("\n")
                        .append("URL            : " + request.getRequestURL().toString()).append("\n")
                        .append("URI            : " + request.getRequestURI()).append("\n")
                        .append("Referer        : " + request.getHeader("referer")).append("\n")
                        .append("Method         : " + request.getMethod()).append("\n")
                        .append("IP             : " + request.getRemoteAddr()).append("\n")
                        .append("User Agent     : " + request.getHeader("User-Agent")).append("\n")
                        .append("Model          : ").append("\n");

                Iterator<String> iterator = modelAndView.getModel().keySet().iterator();
                String key;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    sb
                            .append("\t").append(key).append("\t").append("=").append("\t")
                            .append(modelAndView.getModel().get(key)).append("\n");
                }
                sb.append("View         : " + modelAndView.getViewName()).append("\n");
                sb.append("===================================================================================================").append("\n");
                logger.debug(sb.toString());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        super.postHandle(request, response, handler, modelAndView);
    }
}
