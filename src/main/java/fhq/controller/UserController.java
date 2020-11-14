package fhq.controller;

import fhq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    //注入业务层对象
    @Autowired
    private UserService userService;

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
    @PostMapping("/checkUsername")
    @ResponseBody//将返回值转成json数据
    public Map<String,Object> checkUsername(String username){
        //使用业务层对象调用业务层方法
        Map<String, Object> map = userService.checkUserName(username);
        return map;
    }

    //处理注册
    @PostMapping("/toReg")
    @ResponseBody//将返回值转成json数据
    public Map<String,Object> toReg(String username,String password){

        Map<String, Object> map = userService.toReg(username, password);


        return map;
    }
    //处理登陆
    @PostMapping("toLog")
    @ResponseBody
    public Map<String,Object> toLog(String username,String password){
        Map<String, Object> map = userService.toLog(username, password);
        return map;
    }
    @GetMapping("/toindex")
    public String toindex(){
        return "controlHtml/index";
    }
}
