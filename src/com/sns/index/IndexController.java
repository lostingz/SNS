package com.sns.index;

import com.jfinal.core.Controller;

public class IndexController extends Controller{
    public void index(){
        renderJsp("/login/login.html");
    }
}
