package com.averagejoedev.configs;

import com.averagejoedev.filter.FilterAdmin;
import com.averagejoedev.filter.FilterAuthentication;
import com.averagejoedev.filter.FilterDevice;
import com.averagejoedev.interceptor.InterceptorDebug;
import com.averagejoedev.models.constant.ConstGlobal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by voncount on 7/14/16.
 */
@Configuration
public class ConfigWeb extends WebMvcConfigurerAdapter {

    @Autowired
    private InterceptorDebug interceptorDebug;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + ConstGlobal.UPLOAD_BASE_DIRECTORY).setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS));
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(new Locale("en_US"));
        return cookieLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorDebug);
        registry.addInterceptor(localeChangeInterceptor());
        super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/api/**")
                .allowedMethods("GET","POST","PUT","DELETE","HEAD");
    }

    @Bean
    public FilterRegistrationBean apiDeviceFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new FilterDevice());
        registration.addUrlPatterns("/api/*");
        registration.setName("filterDevice");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean apiAuthFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new FilterAuthentication());
        registration.addUrlPatterns("/api/v1/auth/*", "/api/v1/rtc/*");
        registration.setName("filterAuthentication");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean adminAuthFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new FilterAdmin());
        registration.addUrlPatterns("/admin/api/auth/*");
        registration.setName("filterAdmin");
        registration.setOrder(3);
        return registration;
    }

    @Bean
    public MappingJackson2HttpMessageConverter customJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        converter.setObjectMapper(mapper);
        converter.setPrettyPrint(true);
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, customJacksonHttpMessageConverter());
        super.configureMessageConverters(converters);
    }

}
