package com.softvider.service.user;

import com.softvider.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserAuthentication extends User {

    private final UserModel appUser;

    public UserAuthentication(String username, String password, Collection<? extends GrantedAuthority> authorities, UserModel appUser) {
        super(username, password, authorities);
        this.appUser = appUser;
    }

    public UserModel getAppUser() {
        return appUser;
    }
}
