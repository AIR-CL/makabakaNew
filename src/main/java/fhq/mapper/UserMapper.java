package fhq.mapper;

import fhq.pojo.Advice;
import fhq.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//用户模块持久层
public interface UserMapper {
    //根据用户名查询当前用户信息
    User findUserByUserName(String username);
    //添加新用户
    int regUser(User user);
    //根据用户名和密码查询用户信息
    User loginCheck(@Param("username") String username,@Param("password") String password);
    //根据用户名修改密码
    int toUpdatePassword(@Param("newPassword") String newPassword,@Param("username") String username,@Param("newSalt") String newSalt);
    //根据用户名查询当前用户
    User findUserInfoByUsername(String username);
    //根据用户名修改信息
    int modifyUserInfoByUsername(User user);
    //上传头像
    int uploadFace(@Param("username") String username,@Param("face") String face);
    //提交留言
    int submitAdvice(Advice advice);

     List<Advice> selectAdvice(String adviceType);

}
