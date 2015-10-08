package com.spider;

import java.util.List;

import com.jfinal.core.Controller;

public class ImageViewerController extends Controller{
    public void index(){
        renderJsp("image-viewer/viewer.html");
    }
    public void getUrlList(){
        String url=getPara("dataUrl");
        String param="";
        List<String> imageIdList=ImageUtil.getImageUrl(url, param);
        renderJson(imageIdList);
    }
}
