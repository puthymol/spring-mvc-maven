package com.softvider.model;

import java.util.Map;

public class BaseResponse {

    private int statusCode;
    private String statusMessage;
    private Map<String, Object> data;

    public BaseResponse(){
        this.setStatusCode(200);
        this.setStatusMessage("Success");
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
