package com.sns.login.dao;

import java.util.List;

import com.common.MD5Util;
import com.sns.login.model.User;

public class LoginDao {
    public boolean validateUser(User user){
        boolean flag=false;
        flag=isUserExist(user);
        return flag;
    }
    private boolean isUserExist(User user){
        Object[] params=new Object[2];
        params[0]=user.get("email");
        params[1]=MD5Util.getMD5(user.get("password").toString());
        User u=User.dao.findFirst("select email from users where email=? and password=?", params);
        if(null!=u){
            return true;
        }
        return false;
    }
    public String getUid(User user){
        Object[] params=new Object[2];
        params[0]=user.get("email");
        params[1]=MD5Util.getMD5(user.get("password").toString());
        User u=User.dao.findFirst("select id as uid from users where email=? and password=?", params);
        if(null!=u){
            String uid=u.getInt("uid").toString();
            return uid;
        }
        return "not found";
    }
    public User getUserInfo(String uid){
        Object[] params=new Object[1];
        params[0]=uid;
        User u=User.dao.findFirst("select id,email from users where id=?", params);
        if(null!=u){
            return u;
        }
        return null;
    }
}
