package config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.sns.interceptor.AuthInterceptor;

/**
 * 全局项目配置类
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $1.0$
 */
public class SNSConfig extends JFinalConfig {
    public void configConstant(Constants me) {
        //加载配置信息
        PropKit.use("config/sns.properties");
        //设置模式，true打印调试信息
        me.setDevMode(true);
        me.setEncoding("UTF-8");
    }
    /**
     * 全局路由配置
     * @param me
     */
    public void configRoute(Routes me) {
        me.add("/login", com.sns.login.controller.LoginController.class,"/");
        me.add("/register", com.sns.register.controller.RegisterController.class,"/");
        me.add("/viewer", com.spider.ImageViewerController.class,"/");
    }
    /**
     * 全局插件配置
     * @param me
     */
    public void configPlugin(Plugins me) {
        String host=PropKit.get("host");
        String dbName=PropKit.get("dbName");
        String userName=PropKit.get("userName");
        String password=PropKit.get("password");
        C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://"+host+"/"+dbName+"",userName,password);
        me.add(cp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
        me.add(arp);
        //设置实体和数据库的映射
        SNSMapping.setMapping(arp);
    }

    /**
     * 全局拦截器配置
     * @param me
     */
    public void configInterceptor(Interceptors me) {
         me.add(new AuthInterceptor());
    }

    public void configHandler(Handlers me) {
    }
    /**
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     */
    public static void main(String[] args) {
        JFinal.start("WebRoot", 80, "/", 5);
    }
}