package com.softvider.config.cache;

import com.softvider.model.LoginReqModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class PostKeyGenerator implements KeyGenerator {


    private static final Logger log = LoggerFactory.getLogger(GetKeyGenerator.class);

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getName());
        LoginReqModel request = (LoginReqModel) params[0];
        stringBuilder.append("_").append(request.getUsername());
        stringBuilder.append("_").append(request.getPassword());
        log.info(stringBuilder.toString());
        return stringBuilder.toString();
    }

}
