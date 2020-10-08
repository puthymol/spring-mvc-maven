package com.softvider.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Properties;

public class WebInitializer implements WebApplicationInitializer {

    private static final Logger log = LoggerFactory.getLogger(WebInitializer.class);
    private static Properties config;

    static {
        config = new Properties();
        try {
            log.debug("Loading classpath:META-INF/application.properties");
            config.load(new ClassPathResource("/META-INF/application.properties").getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onStartup(ServletContext servletContext) {

        this.registerContextMvc(servletContext);
        this.registerCharacterEncoding(servletContext);
        if(config != null) {
            config.clear();
            config = null;
        }
    }

    private void registerContextMvc(ServletContext servletContext) {
        WebApplicationContext webContext = this.getWebContext();
        servletContext.addListener(new ContextLoaderListener(webContext));
        ServletRegistration.Dynamic registration = servletContext.addServlet(config.getProperty("softvider.servlet.name"), this.getDispatcherServlet(webContext));
        registration.addMapping(config.getProperty("softvider.servlet.mapping"));
        registration.setLoadOnStartup(1);
    }

    private DispatcherServlet getDispatcherServlet(WebApplicationContext webContext) {
        log.debug("Initializing DispatcherServlet");
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return dispatcherServlet;
    }

    private WebApplicationContext getWebContext() {
        log.debug("Initializing AnnotationConfigWebApplicationContext");
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.scan(config.getProperty("softvider.servlet.configLocation"));
        return webContext;
    }

    private void registerCharacterEncoding(ServletContext servletContext) {
        log.debug("Initializing CharacterEncodingFilter");
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
        encodingFilter.addMappingForUrlPatterns(
                EnumSet.of(
                        DispatcherType.REQUEST,
                        DispatcherType.FORWARD),
                true,
                config.getProperty("softvider.characterEncoding.mapping")
        );
    }
}
