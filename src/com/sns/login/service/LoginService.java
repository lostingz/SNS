package com.sns.login.service;

import com.sns.login.model.User;

public interface LoginService {
    /**
     * 登录验证
     * @param user 用户对象
     * @return 验证成功返回true，否则返回false
     */
    public boolean validate(User user);
    public String getUserId(User user);
}
