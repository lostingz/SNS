package com.sns.login.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.sns.login.model.User;
import com.sns.login.service.LoginService;
import com.sns.login.service.impl.LoginServiceImpl;
import com.sns.login.validate.LoginValidator;

public class LoginController extends Controller{
    @Before(LoginValidator.class)
    public void index(){
        User user=getModel(User.class);
        LoginService loginService=new LoginServiceImpl();
        boolean flag=loginService.validate(user);
        String result="";
        if(flag){
            result="登录成功";
        }else{
            result="登录失败";
        }
        renderText(result);
    }
    @ActionKey("/test")
    public void hello(){
        String a="asdfasd";
        renderText(a);
    }
}
