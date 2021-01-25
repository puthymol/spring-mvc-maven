package com.softvider.service.user.impl;

import com.softvider.model.AuthenticationRequest;
import com.softvider.model.UserModel;
import com.softvider.service.user.UserAuthentication;
import com.softvider.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UserService userService;

    @Override
    public UserAuthentication loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationRequest reqModel = new AuthenticationRequest();
        reqModel.setUsername(username);
        try {
            UserModel userModel = userService.getUserByUsername(reqModel);
            return new UserAuthentication(userModel.getUsername(), userModel.getPassword(), new ArrayList<>(), userModel);
        } catch (UsernameNotFoundException e) {
            log.info("Error => {}", e.getMessage());
            throw e;
        }
    }
}
