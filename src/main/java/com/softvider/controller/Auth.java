package com.softvider.controller;

import com.softvider.model.AuthenticationRequest;
import com.softvider.model.BaseResponse;
import com.softvider.service.UserDetailsServiceImpl;
import com.softvider.utils.AppUtilException;
import com.softvider.utils.AppUtilExceptionHandler;
import com.softvider.utils.ExceptionBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Auth extends AppUtilExceptionHandler {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

//    @PostMapping(value = "oauth/token", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public BaseResponse HomeAPI(AuthenticationRequest authenticationRequest) throws AppUtilException {
//        try{
//            authenticationManager
//                    .authenticate(
//                            new UsernamePasswordAuthenticationToken(
//                                    authenticationRequest.getUsername(),
//                                    authenticationRequest.getPassword()
//                            )
//                    );
//        }catch (BadCredentialsException e){
//            ExceptionBaseResponse response = new ExceptionBaseResponse();
//            response.setStatusCode(201);
//            response.setStatusMessage(e.getMessage());
//            throw new AppUtilException(response);
//        }
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//
//        Map<String, Object> jsonString = new HashMap<>();
//        jsonString.put("jwt", "eyhfhsfndjskafdsajkfdjsajfkjfdjskfdskjahfsdajklh");
//        BaseResponse response = new BaseResponse();
//        response.setStatusCode(200);
//        response.setData(jsonString);
//        return response;
//    }

}
