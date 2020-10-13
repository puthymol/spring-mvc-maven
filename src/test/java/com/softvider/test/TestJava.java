package com.softvider.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import net.minidev.json.JSONObject;

import java.io.IOException;

public class TestJava {

    @Test
    public void ClassToString() {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            Book cleanCode = new Book("Clean Code", null, 42);

            String jsonString = mapper.writeValueAsString(cleanCode);
            JSONObject jsonObject =  mapper.readValue(jsonString, JSONObject.class);

            System.out.println(jsonString);
            System.out.println(jsonObject);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

