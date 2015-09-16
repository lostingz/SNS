package com.sns.message.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * 消息实体
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class Message extends Model<Message>{
    private static final long serialVersionUID = 1934915031797352033L;
    public static final Message dao=new Message();
}
