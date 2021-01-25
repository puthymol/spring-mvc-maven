package com.softvider.config.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.controller.HomeController;
import com.softvider.service.user.UserAuthentication;
import com.softvider.utils.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationSecurityContext {
    private static final Logger log = LoggerFactory.getLogger(ApplicationSecurityContext.class);

    public Authentication getAuth() {
        final var context = SecurityContextHolder.getContext();
        return context == null ? null : context.getAuthentication();
    }

    public String authenticatedUser() {
        try {
            Authentication authentication = getAuth();
            Object principle = authentication == null ? null : authentication.getPrincipal();
            JsonNode jsonNode = AppUtil.convert(principle, JsonNode.class);
            String username = jsonNode.findPath("username").textValue();
            UserAuthentication userAuthentication = (UserAuthentication) principle;
            log.info(userAuthentication.getAppUser().getUsername());
            log.info(userAuthentication.getAppUser().getFirstName());
            return username;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
