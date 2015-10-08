package com.sns.login.service.impl;

import com.sns.login.dao.LoginDao;
import com.sns.login.model.User;
import com.sns.login.service.LoginService;

public class LoginServiceImpl implements LoginService{
    private static LoginDao loginDao=new LoginDao();
    @Override
    public boolean validate(User user) {
        boolean flag=loginDao.validateUser(user);
        return flag;
    }
    @Override
    public String getUserId(User user) {
        String uid=loginDao.getUid(user);
        return uid;
    }
    public User getUserInfoByUid(String uid){
        return loginDao.getUserInfo(uid);
    }
}
