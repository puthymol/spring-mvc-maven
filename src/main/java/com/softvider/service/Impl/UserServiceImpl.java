package com.softvider.service.Impl;

import com.softvider.controller.HomeController;
import com.softvider.model.AuthenticationRequest;
import com.softvider.model.BaseResponse;
import com.softvider.model.UserModel;
import com.softvider.service.UserService;
import com.softvider.utils.AppUtilsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
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

    @Override
    public UserModel getUserByUsername(AuthenticationRequest reqModel) throws UsernameNotFoundException {
        try {
            if(reqModel.getUsername().equals("softvider")){
                UserModel userModel = new UserModel();
                userModel.setUsername(reqModel.getUsername());
                return userModel;
            }else{
                throw new UsernameNotFoundException("User Not Found");
            }
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("User Not Found");
        }
    }
}
