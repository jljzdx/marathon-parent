package com.newera.marathon.base.sso.service;

import com.newera.marathon.base.sso.entity.AdminUser;

public interface UserService {

    AdminUser findByUserName(String userName);
}
