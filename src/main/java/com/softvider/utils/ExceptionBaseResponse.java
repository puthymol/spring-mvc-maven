package com.softvider.utils;

import com.softvider.model.BaseResponse;

import java.util.HashMap;
import java.util.Map;

public class ExceptionBaseResponse extends BaseResponse {
    private Map<String, Object> errors = new HashMap<>();

    public ExceptionBaseResponse() {
        this.setStatusCode(201);
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }
}
