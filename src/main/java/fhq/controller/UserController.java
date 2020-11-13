package fhq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    //跳转到注册页面
    @GetMapping("/reg")
    public String toRegPage(){
        return "web/register";
    }

    //跳转登陆界面
    @GetMapping("/log")
    public String toLogPage(){
        return "web/login";
    }
}
