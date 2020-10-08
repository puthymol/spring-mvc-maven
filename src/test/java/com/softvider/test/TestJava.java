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

@JsonInclude(JsonInclude.Include.NON_NULL)
class Book {

    private String title;

    private String author;
    private int price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
