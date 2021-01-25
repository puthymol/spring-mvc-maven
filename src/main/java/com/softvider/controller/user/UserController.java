package com.softvider.controller.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.config.security.ApplicationSecurityContext;
import com.softvider.model.BaseResponse;
import com.softvider.model.UserModel;
import com.softvider.service.user.impl.UserServiceImpl;
import com.softvider.utils.AppUtil;
import com.softvider.utils.AppUtilException;
import com.softvider.utils.AppUtilValidationException;
import com.softvider.utils.ExceptionBaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController extends AppUtil {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final ApplicationSecurityContext applicationSecurityContext = new ApplicationSecurityContext();
    private final UserServiceImpl userService = new UserServiceImpl();

    @RequestMapping(value = "/user/get", method = RequestMethod.POST)
    public BaseResponse getUser(@RequestBody JsonNode jsonNode,  HttpServletRequest httpServletRequest) throws AppUtilException {
        try {
            UserModel request = AppUtil.convertValidate(jsonNode, UserModel.class);
            log.info("Token {}", httpServletRequest.getHeader("authorization"));

            String username =  applicationSecurityContext.authenticatedUser();
            log.info("Yo {}", username);

            return userService.execute(request);
        } catch (AppUtilValidationException e) {
            ExceptionBaseResponse response = new ExceptionBaseResponse();
            response.setStatusCode(201);
            response.setErrors(e.getErrors());
            throw new AppUtilException(response);
        }
    }

    @ExceptionHandler(AppUtilException.class)
    public ResponseEntity<ExceptionBaseResponse> handleMethodArgumentNotValidException(AppUtilException exception) {
        return new ResponseEntity<>(exception.getTemplateStrategy(), HttpStatus.OK);
    }

}
