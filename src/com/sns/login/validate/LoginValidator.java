package com.sns.login.validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator {

    @Override
    protected void validate(Controller paramController) {
        validateRequiredString("user.email", "nameMsg", "请输入邮箱");
        validateRequiredString("user.password", "passMsg", "请输入密码");
    }

    @Override
    protected void handleError(Controller c) {
        c.renderText("登录失败，请输入正确的邮箱和密码");
    }

}
