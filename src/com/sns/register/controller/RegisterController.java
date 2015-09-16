package com.sns.register.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.sns.login.model.User;
import com.sns.register.service.RegisterServiceImpl;
import com.sns.register.validate.RegisterValidator;

public class RegisterController extends Controller{
    @Before(RegisterValidator.class)
    public void index(){
        User user=getModel(User.class);
        boolean flag=new RegisterServiceImpl().register(user);
        String result="";
        if(flag){
            result="注册成功";
        }else{
            result="注册失败";
        }
        renderText(result);
    }
}