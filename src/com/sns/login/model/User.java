package com.sns.login.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * 用户模型操作类
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class User extends Model<User>{
    private static final long serialVersionUID = 2863364569753415058L;
    public static final User dao=new User();
}
