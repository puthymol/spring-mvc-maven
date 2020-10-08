package com.softvider.config;

import com.softvider.config.cache.GetKeyGenerator;
import com.softvider.config.cache.GetWithParamKeyGenerator;
import com.softvider.config.cache.PostKeyGenerator;
import net.sf.ehcache.Ehcache;
import org.apache.http.client.cache.HttpCacheStorage;
import org.apache.http.impl.client.cache.CacheConfig;
import org.apache.http.impl.client.cache.ehcache.EhcacheHttpCacheStorage;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;
import javax.inject.Inject;

@EnableWebMvc
@Configuration
@EnableCaching
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

    @Bean
    public CacheManager cacheManager() {
        EhCacheCacheManager cacheManager = new EhCacheCacheManager();
        cacheManager.setCacheManager(this.cacheManagerFactoryBean().getObject());
        return cacheManager;
    }

    @Bean
    public EhCacheManagerFactoryBean cacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource(this.env.getProperty("softvider.cache.configLocation")));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

    @Bean
    public CacheConfig cacheConfig() {
        return CacheConfig
                .custom()
                .setMaxObjectSize(Integer.parseInt(this.env.getProperty("softvider.cache.maxObjectSize")))
                .setMaxCacheEntries(Integer.parseInt(this.env.getProperty("softvider.cache.maxCacheEntries")))
                .build();
    }

    @Bean
    public HttpCacheStorage httpCacheStorage() {
        Ehcache ehcache = (Ehcache) this.cacheManager().getCache("httpClient").getNativeCache();
        HttpCacheStorage result = new EhcacheHttpCacheStorage(ehcache);
        return result;
    }

    @Bean("getKeyGenerator")
    public KeyGenerator getKeyGenerator() {
        return new GetKeyGenerator();
    }

    @Bean("getWithParamKeyGenerator")
    public KeyGenerator getWithParamKeyGenerator() {
        return new GetWithParamKeyGenerator();
    }

    @Bean("postKeyGenerator")
    public KeyGenerator postKeyGenerator() {
        return new PostKeyGenerator();
    }
}