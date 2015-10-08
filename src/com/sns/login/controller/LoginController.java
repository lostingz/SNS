package com.sns.login.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.sns.login.model.User;
import com.sns.login.service.LoginService;
import com.sns.login.service.impl.LoginServiceImpl;
import com.sns.login.validate.LoginValidator;

public class LoginController extends Controller{
    private LoginService loginService=new LoginServiceImpl();
    @Before(LoginValidator.class)
    public void index(){
        User user=getModel(User.class);
        boolean flag=loginService.validate(user);
        String result="";
        if(flag){
            setSessionAttr("uid",loginService.getUserId(user));
            result="登录成功";
            renderJsp("index/index.html");
        }else{
            result="登录失败";
            render("login.html");
        }
    }
    private String getUserId(){
        String uid=getSessionAttr("uid");
        return uid;
    }
    public void getUserInfo(){
       User userInfo=loginService.getUserInfoByUid(getUserId());
       renderJson(userInfo);
    }
    public void logout(){
        getSession().invalidate();
        renderText("logout");
    }
}
