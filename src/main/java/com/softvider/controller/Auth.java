package com.softvider.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Auth {
    @RequestMapping(value = "oauth/token", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> HomeAPI() {
        Map<String, Object> jsonString = new HashMap<>();
        jsonString.put("provider", "Softvider Security");
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }
}
