package demo.controller;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

import demo.model.Student;

public class HelloController extends Controller {
    /**
     * 默认请求方法
     */
    public void index() {
        renderText("Hello JFinal World."+PropKit.get("userName"));
    }
    /**
     * 获取请求参数测试
     */
    @ActionKey("/hellotest")
    public void test(){
        String name=getPara("name");
        renderText("test."+name);
    }
    /**
     * 跳转测试
     */
    public void testPage(){
        renderJsp("/demo-page/hello.jsp");
    }
    /**
     * json转换测试
     */
    public void jsonTest(){
        Student stu=new Student();
        stu.setAge(11);
        stu.setName("zheng");
        List<Student> list=new ArrayList<Student>();
        list.add(stu);
        list.add(stu);
        renderJson(list);
    }
    /**
     * 文件下载测试
     */
    public void downloadTest(){
        String filePath="/demo-page/test.zip";
        renderFile(filePath);
    }
}