package com.softvider.utils;

public class AppUtilsException extends Exception {

    private final ExceptionBaseResponse response;

    public AppUtilsException(ExceptionBaseResponse response) {
        this.response = response;
    }

    public ExceptionBaseResponse getTemplateStrategy() {
        return response;
    }
}
