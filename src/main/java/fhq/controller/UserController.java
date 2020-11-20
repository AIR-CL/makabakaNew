package fhq.controller;

import fhq.pojo.User;
import fhq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    //跳转主页面
    @GetMapping("/index")
    public String toIndexPage(){
        return "web/index";
    }
//    跳转用户中心
    @GetMapping("/userCenter")
    public String toUserCenterPage(){ return "web/userCenter";}
    //跳转播放页面
    @GetMapping("/broadcast")
    public String toBoardcastPage(){ return "web/broadcast";}
    //跳转我的收藏
    @GetMapping("/myCollection")
    public String toMyCollectionPage(){ return "web/myCollection";}
//跳转到留言页
    @GetMapping("/advice")
    public String toAdvicePage(){
        return "web/advice";
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
    @PostMapping("/toLog")
    @ResponseBody
    public Map<String,Object> toLog(String username,String password,HttpServletRequest req){
        Map<String, Object> map = userService.toLog(username, password,req);
        return map;
    }
//更新密码
    @PostMapping("/toUpdatePwd")
    @ResponseBody
    public Map<String,Object> toUpdatePwd(String oldPassword,String newPassword,String confirmPassword,HttpServletRequest req){
        Map<String, Object> map = userService.toUpdatePassword(oldPassword, newPassword, confirmPassword, req);
        return map;
    }
    @GetMapping("/findUserInfo")
    @ResponseBody
    //根据username查询当前用户信息
    public User findUserInfoByName(HttpServletRequest request){
        //获取用户名
        String username = (String)request.getSession().getAttribute("username");
        User user = userService.findUserInfoByUsername(username);
        return user;
    }
    //根据用户名修改信息
    @PostMapping("/modifyUserInfoByUsername")
    @ResponseBody
    public  Map<String,Object> modifyUserInfoByName(String userName, String address, String tel, String sex,
                                                    String birthday,String edu,String signature,String industry,String email){
        Map<String, Object> map = userService.modifyUserByName(userName,address,tel,sex,
                birthday,edu,signature,industry,email);
        return map;
    }
    //头像上传
    @PostMapping("/toUpload")
    @ResponseBody
    public Map<String,Object>toUpload(MultipartFile file,HttpServletRequest request){
        System.out.println(file+"2323");
        Map<String, Object> map = userService.uploadFace(file, request);
        return map;
    }

}
