package com.softvider.config.cache;

import net.sf.ehcache.Ehcache;
import org.apache.http.client.cache.HttpCacheStorage;
import org.apache.http.impl.client.cache.ehcache.EhcacheHttpCacheStorage;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import javax.inject.Inject;

@Configuration
@EnableCaching
public class CacheConfig {
    private final Environment env;

    @Inject
    public CacheConfig(Environment env) {
        this.env = env;
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
