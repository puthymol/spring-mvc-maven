package com.softvider.service;

import com.softvider.controller.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HomeService {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    public Map<String, Object> Home() {
        Map<String, Object> jsonString = new HashMap<>();
        jsonString.put("provider", "Softvider");
        log.info("Response => {} :" +jsonString);
        return jsonString;
    }
}
