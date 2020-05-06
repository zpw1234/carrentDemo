package com.sxt.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("desk")
public class DeskManager {

    /**
     * 跳转到工作台的页面
     */
    @RequestMapping("toDeskManager")
    public String toDeskManager(){
        return "system/main/deskManager";
    }

}
