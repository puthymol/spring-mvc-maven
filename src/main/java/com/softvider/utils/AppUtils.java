package com.softvider.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class AppUtils {

    static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static  <T> T convert(Object object, Class<T> clazz) throws AppUtilsValidationException {
        T request = objectMapper.convertValue(object, clazz);
        Set<ConstraintViolation<T>> violations = validator.validate(request);
        if(!violations.isEmpty()) {
            throw new AppUtilsValidationException(violations);
        }
        return request;
    }
}
