package com.softvider.controller;

import com.softvider.service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    HomeService homeService = new HomeService();

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, Object> Home() {
        return homeService.Home();
    }


    @RequestMapping(value = "/{fileName}", method = RequestMethod.GET)
    public Map<String, Object> Home(@PathVariable(value="fileName") String fileName) {
        Map<String, Object> jsonString = new HashMap<>();
        jsonString.put("provider", "Softvider");
        writeLogIntoFile(jsonString, fileName);
        return jsonString;
    }

    private void writeLogIntoFile(Map<String, Object> jsonString, String fileName){
        try {
            String dir = System.getenv("CATALINA_HOME");
            File actualFile = new File(dir+"/logs/spring-mvc-maven", fileName+".txt");
            FileWriter myWriter = new FileWriter(actualFile);
            myWriter.write(jsonString.toString());
            myWriter.close();
            log.info("Successfully wrote to the file.");
        } catch (IOException e) {
            log.info("An error occurred.");
            e.printStackTrace();
        }
    }
}
