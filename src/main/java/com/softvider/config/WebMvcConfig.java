package com.softvider.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;
import javax.inject.Inject;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.softvider.*")
@PropertySource(value = "classpath:META-INF/application.properties", encoding = "UTF-8")
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] DEFAULT_ALLOWED_METHODS = new String[]{"POST", "GET", "HEAD", "OPTIONS"};
    private static final String[] DEFAULT_ALLOWED_HEADERS = new String[]{"Origin", "Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers"};

    private final Environment env;

    @Inject
    public WebMvcConfig(Environment env) {
        this.env = env;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(this.env.getProperty("softvider.cors.mapping"))
                .allowedOrigins(this.env.getProperty("softvider.cors.allowedOrigins"))
                .allowedMethods(DEFAULT_ALLOWED_METHODS)
                .allowedHeaders(DEFAULT_ALLOWED_HEADERS);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(true);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
    }
}
