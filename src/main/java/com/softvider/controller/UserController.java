package com.softvider.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.model.BaseResponse;
import com.softvider.model.UserModel;
import com.softvider.service.Impl.UserServiceImpl;
import com.softvider.utils.AppUtils;
import com.softvider.utils.AppUtilsException;
import com.softvider.utils.AppUtilsValidationException;
import com.softvider.utils.ExceptionBaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends AppUtils {

    private final UserServiceImpl userService = new UserServiceImpl();

    @RequestMapping(value = "/user/get", method = RequestMethod.POST)
    public BaseResponse getUser(@RequestBody JsonNode jsonNode) throws AppUtilsException {
        try {
            UserModel request = AppUtils.convert(jsonNode, UserModel.class);
            return userService.execute(request);
        } catch (AppUtilsValidationException e) {
            ExceptionBaseResponse response = new ExceptionBaseResponse();
            response.setStatusCode(201);
            response.setErrors(e.getErrors());
            throw new AppUtilsException(response);
        }
    }

    @ExceptionHandler(AppUtilsException.class)
    public ResponseEntity<ExceptionBaseResponse> handleMethodArgumentNotValidException(AppUtilsException exception) {
        return new ResponseEntity<>(exception.getTemplateStrategy(), HttpStatus.OK);
    }
}
