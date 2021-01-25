package com.softvider.service.security.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.service.security.StudentService;
import com.softvider.utils.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public JsonNode exec() {
        Map<String, Object> jsonString = new HashMap<>();
        jsonString.put("student", "Puthy");
        log.info("Response => {}", jsonString);
        return AppUtil.convert(jsonString, JsonNode.class);
    }
}
