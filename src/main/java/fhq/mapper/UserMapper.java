package fhq.mapper;

import fhq.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

//用户模块持久层
@Repository
public interface UserMapper {
    //根据用户名查询当前用户信息
    User findUserByUserName(String username);
    int regUser(User user);


}
