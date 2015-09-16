package com.sns.register.validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.sns.login.model.User;

public class RegisterValidator extends Validator{

    @Override
    protected void handleError(Controller c) {
        c.renderText("验证失败");
    }

    @Override
    protected void validate(Controller c) {
        validateRequiredString("user.email", "nameMsg", "请输入邮箱");
        validateRequiredString("user.password", "passMsg", "请输入密码");
        if(isEmailExist(c.getModel(User.class))){
            validateRequiredString("emailExist", "passMsg", "邮箱重复");
        }
    }
    private boolean isEmailExist(User user){
        Object[] params=new Object[1];
        params[0]=user.get("email");
        User u=User.dao.findFirst("select email from users where email=?", params);
        if(null!=u){
            return true;
        }
        return false;
    }

}
