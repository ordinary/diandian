package com.diandian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhutao on 14/10/12.
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping("/self")
    public String self(){
        return "user/self";
    }



}
