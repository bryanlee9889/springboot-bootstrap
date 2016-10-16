package com.averagejoedev.aspect;

import com.averagejoedev.models.form.Form;
import com.averagejoedev.utils.ServletUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by voncount on 9/26/16.
 */
@Aspect
@Component
public class ApiLogging {

    private static final Logger logger = LoggerFactory.getLogger(ApiLogging.class);

    private HttpServletRequest httpRequest;

    public ApiLogging(HttpServletRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    /**
     * Hijack loggind and auth data
     * @param joinPoint
     */
    @Before(
            value = "execution(* kr.co.trams.api.controllers.*.*.*(..))"
    )
    public void apiHijack(final JoinPoint joinPoint) {
        try {
            Form form = (Form) joinPoint.getArgs()[0];
            try {
                String[] loggingParams = ServletUtils.getMobileLogging(httpRequest);
                form.setUuid(loggingParams[0]);
                form.setVersion(loggingParams[1]);
                form.setOs(loggingParams[2]);
                form.setLocale(loggingParams[3]);
            } catch (Exception e1) {
                logger.error("Hijack logging data failed at " + joinPoint.getSignature().getName());
            }

            try {
                Map<String, Object> auth = ServletUtils.getAuth(httpRequest);
                form.setAuthId((int) auth.get("id"));
                form.setAuthUsername((String) auth.get("username"));
            } catch (Exception e2) {
                logger.error("Hijack auth data failed at " + joinPoint.getSignature().getName());
            }

        } catch (Exception e) {
            logger.error("Hijack failed at " + joinPoint.getSignature().getName());
        }
    }

}
