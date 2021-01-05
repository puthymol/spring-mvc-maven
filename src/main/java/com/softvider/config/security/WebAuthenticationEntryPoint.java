package com.softvider.config.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@Component("restAuthenticationEntryPoint")
//public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
//            throws IOException, ServletException {
//        // 401
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
//    }
//
//    @ExceptionHandler(value = {AccessDeniedException.class})
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//                         AccessDeniedException accessDeniedException) throws IOException {
//        // 403
//        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed : " + accessDeniedException.getMessage());
//    }
//
//    @ExceptionHandler (value = {Exception.class})
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//                         Exception exception) throws IOException {
//        // 500
//        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error : " + exception.getMessage());
//    }
//
//}

public class WebAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
    }
}
