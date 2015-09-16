package com.sns.register.service;

import com.sns.login.model.User;

public interface RegisterService {
    /**
     * 注册
     * @param user 用户对象
     * @return 注册成功返回true
     */
    public boolean register(User user);
}
