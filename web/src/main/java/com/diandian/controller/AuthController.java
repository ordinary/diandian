package com.diandian.controller;

import com.diandian.service.WeixinMessageDigest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhutao on 2014/9/24.
 */
@RequestMapping("/")
@Controller
public class AuthController {

    @ResponseBody
    @RequestMapping(produces="text/html;charset=UTF-8")
    public String auth(String signature,String timestamp,String nonce,String echostr){
        WeixinMessageDigest wxDigest = WeixinMessageDigest.getInstance();
        if(wxDigest.validate(signature,timestamp,nonce)){
            return echostr;
        }
        return "error";
    }


    @ResponseBody
    @RequestMapping(method = { RequestMethod.POST },produces="text/html;charset=UTF-8")
    public String dispatch(HttpServletRequest request, HttpServletResponse response){

        return "";
    }

}
