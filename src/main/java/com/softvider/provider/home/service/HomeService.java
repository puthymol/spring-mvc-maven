package com.softvider.provider.home.service;

import com.softvider.utils.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class HomeService {
    private static final Logger log = LoggerFactory.getLogger(HomeService.class);

    public Map<String, Object> Home() {
        Map<String, Object> jsonString = new HashMap<>();
        jsonString.put("provider", "Softvider");
        log.info("Response => {}", AppUtil.toJSON(jsonString));
        return jsonString;
    }
}
