package com.diandian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhutao on 14/11/2.
 */
@RequestMapping("/statistics")
@Controller
public class StatisticsController {

    @RequestMapping("/visit/total")
    @ResponseBody
    public int visitTotal(){
        return 1000;
    }
}
