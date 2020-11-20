package fhq.service;

import fhq.pojo.Advice;
import fhq.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

//用户模块业务层接口
public interface UserService {
    //根据用户名查询用户信息
    Map<String,Object> checkUserName(String username);
//注册
    Map<String,Object> toReg(String username,String password);
//根据用户名和密码查询用户信息
    Map<String, Object> toLog(String username, String password, HttpServletRequest req);
    //根据用户名修改密码
    Map<String,Object> toUpdatePassword(String oldPassword,String newPassword,String confirmPassword,HttpServletRequest request);
    //根据用户名查询信息
    User findUserInfoByUsername(String username);
    //根据用户名修改密码
    Map<String,Object> modifyUserByName(String userName, String address, String tel, String sex,
                                        String birthday,String edu,String signature,String industry,String email);
    //上传头像
    Map<String,Object> uploadFace(MultipartFile file,HttpServletRequest request);
    //提交留言
    Map<String,Object> submitAdvice(Integer userId,String adviceType,String adviceTitle,String adviceContent);
    //查询留言
    List<Advice> selectAdvice(String adviceType);
}
