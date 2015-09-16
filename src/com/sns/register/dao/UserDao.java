package com.sns.register.dao;

import com.common.MD5Util;
import com.common.base.DateUtil;
import com.sns.login.model.User;

public class UserDao {
    /**
     * 获取加密后的密码
     * @param str 加密前字符串
     * @return 加密后的密码值
     */
    public String getEncryptPwd(String str){
        return MD5Util.getMD5(str);
    }
    /**
     * 保存用户到数据库
     * @param user
     * @return
     */
    public boolean addUser(User user){
        user.set("password",getEncryptPwd(user.get("password").toString()));
        user.set("name",user.get("email"));
        //user.set("ctime",DateUtil.getCurrentTime());
        return user.save();
    }
}
