package com.sns.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 权限控制类
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class AuthInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation invocation) {
        System.out.println("有权限,执行校验");
        //执行权限校验代码
        System.out.println("权限通过，开始执行程序");
        invocation.invoke();
        System.out.println("操作结束");
    }
}
