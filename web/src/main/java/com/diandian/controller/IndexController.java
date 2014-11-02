package com.diandian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhutao on 14/11/2.
 */
@RequestMapping("/index")
@Controller
public class IndexController {

    @RequestMapping("")
    public String index(){
        return "index";
    }
}
