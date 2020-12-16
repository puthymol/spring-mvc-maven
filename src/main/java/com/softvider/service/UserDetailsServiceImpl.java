package com.softvider.service;

import com.softvider.model.AuthenticationRequest;
import com.softvider.model.UserModel;
import com.softvider.utils.AppUtilsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationRequest reqModel = new AuthenticationRequest();
        reqModel.setUsername(username);
        try {
            UserModel userModel = userService.getUserByUsername(reqModel);
            return new User(userModel.getUsername(), "$2a$04$NlGsa.TIUrRHcD1Rs6Tkc.JV7WYMQosK.OD3w5FObS3POuYta6ATa", new ArrayList<>());
        } catch (UsernameNotFoundException e) {
            log.info("Error => {}", e.getMessage());
            throw e;
        }
    }
}
