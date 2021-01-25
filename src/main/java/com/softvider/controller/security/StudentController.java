package com.softvider.controller.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.service.security.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "student", method = RequestMethod.GET)
    public ResponseEntity<JsonNode> Student() {
        JsonNode result = studentService.exec();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "anonymous/student", method = RequestMethod.GET)
    public ResponseEntity<JsonNode> AnonymousStudent() {
        JsonNode result = studentService.exec();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
