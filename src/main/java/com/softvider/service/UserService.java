package com.softvider.service;

import com.softvider.model.AuthenticationRequest;
import com.softvider.model.BaseResponse;
import com.softvider.model.UserModel;
import com.softvider.utils.AppUtilsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    BaseResponse execute(UserModel userModel);

    UserModel getUserByUsername(AuthenticationRequest authenticationRequest) throws UsernameNotFoundException;
}
