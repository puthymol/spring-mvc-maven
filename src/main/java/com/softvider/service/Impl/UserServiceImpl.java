package com.softvider.service.Impl;

import com.softvider.controller.HomeController;
import com.softvider.model.BaseResponse;
import com.softvider.model.UserModel;
import com.softvider.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Override
    public BaseResponse execute(UserModel userModel)  {
        log.info("Request => {}", userModel.toString());
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setStatusMessage(userModel.getFirstName());
        return response;
    }
}
