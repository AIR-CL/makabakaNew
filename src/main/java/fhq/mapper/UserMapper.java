package fhq.mapper;

import fhq.pojo.User;

//用户模块持久层
public interface UserMapper {
    //根据用户名查询当前用户信息
    User findUserByUserName(String username);
    int regUser(User user);
}
