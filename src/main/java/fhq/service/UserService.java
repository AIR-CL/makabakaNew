package fhq.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//用户模块业务层接口
public interface UserService {
    //根据用户名查询用户信息
    Map<String,Object> checkUserName(String username);
//注册
    Map<String,Object> toReg(String username,String password);
//根据用户名和密码查询用户信息
    Map<String, Object> toLog(String username, String password, HttpServletRequest req);
}
