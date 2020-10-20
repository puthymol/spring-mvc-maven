package com.softvider.service;

import com.softvider.model.BaseResponse;
import com.softvider.model.UserModel;

public interface UserService {

    BaseResponse execute(UserModel userModel);
}
