package fhq.mapper;

import fhq.pojo.User;
import org.apache.ibatis.annotations.Param;

//用户模块持久层
public interface UserMapper {
    //根据用户名查询当前用户信息
    User findUserByUserName(String username);
    //添加新用户
    int regUser(User user);
    //根据用户名和密码查询用户信息
    User loginCheck(@Param("username") String username,@Param("password") String password);

}
