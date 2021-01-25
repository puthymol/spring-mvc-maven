package com.softvider.service.user.impl;

import com.softvider.controller.HomeController;
import com.softvider.model.AuthenticationRequest;
import com.softvider.model.BaseResponse;
import com.softvider.model.UserModel;
import com.softvider.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    public UserServiceImpl() {
    }

    @Override
    public BaseResponse execute(UserModel userModel)  {
        log.info("Request => {}", userModel.toString());
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setStatusMessage(userModel.getFirstName());
        return response;
    }

    @Override
    public UserModel getUserByUsername(AuthenticationRequest reqModel) throws UsernameNotFoundException {
        try {
            if(reqModel.getUsername().equals("puthy")){
                UserModel userModel = new UserModel();
                userModel.setUsername(reqModel.getUsername());

                // These data got from database
                userModel.setFirstName("Puthy");
                userModel.setPassword("$2a$04$NlGsa.TIUrRHcD1Rs6Tkc.JV7WYMQosK.OD3w5FObS3POuYta6ATa"); // raw=Softvider@123
                return userModel;
            }else{
                throw new UsernameNotFoundException("User Not Found");
            }
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("User Not Found");
        }
    }
}
