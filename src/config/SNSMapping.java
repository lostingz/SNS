package config;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * 数据库表与实体映射处理类
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class SNSMapping {
    public static void setMapping(ActiveRecordPlugin arp){
        //用户表
        arp.addMapping("users", com.sns.login.model.User.class);
    }
}
