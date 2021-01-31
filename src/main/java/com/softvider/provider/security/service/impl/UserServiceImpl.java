package com.softvider.provider.security.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.provider.home.controller.HomeController;
import com.softvider.provider.security.model.adapter.AuthenticationAdapter;
import com.softvider.model.BaseResponse;
import com.softvider.provider.security.model.adapter.UserAdapter;
import com.softvider.provider.security.service.UserService;
import com.softvider.utils.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Override
    public BaseResponse execute(UserAdapter userAdapter)  {
        log.info("Request => {}", userAdapter.toString());
        return  new BaseResponse(AppUtil.convert(userAdapter, JsonNode.class));
    }

    @Override
    public UserAdapter getUserByUsername(AuthenticationAdapter reqModel) throws UsernameNotFoundException {
        try {
            if(reqModel.getUsername().equals("puthy")){
                UserAdapter userAdapter = new UserAdapter();
                userAdapter.setUsername(reqModel.getUsername());

                // These data got from database
                userAdapter.setFirstName("Puthy");
                userAdapter.setPassword("$2a$04$NlGsa.TIUrRHcD1Rs6Tkc.JV7WYMQosK.OD3w5FObS3POuYta6ATa"); // raw=Softvider@123
                return userAdapter;
            }else{
                throw new UsernameNotFoundException("User Not Found");
            }
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("User Not Found");
        }
    }
}
