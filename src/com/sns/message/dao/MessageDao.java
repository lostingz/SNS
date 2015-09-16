package com.sns.message.dao;

import com.common.base.DateUtil;
import com.sns.message.model.Message;

public class MessageDao {
    /**
     * 添加一条信息
     * @param msg 信息实体
     * @return 添加成功返回true，否则返回false
     */
    public boolean addMessage(Message msg){
        msg.set("ctime",DateUtil.getCurrentTime());
        boolean flag=msg.save();
        return flag;
    }
}
