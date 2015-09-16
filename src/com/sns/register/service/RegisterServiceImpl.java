package com.sns.register.service;

import com.sns.login.model.User;
import com.sns.register.dao.UserDao;

public class RegisterServiceImpl implements RegisterService {
    private UserDao userDao=new UserDao();
    @Override
    public boolean register(User user) {
        return userDao.addUser(user);
    }

}
