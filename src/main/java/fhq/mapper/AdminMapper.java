package fhq.mapper;

import fhq.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminMapper {
    //查询所有用户
    List<User> findAllUsers();
    //用户的模糊查询
    List<User> findUsersLike(User user);
    //修改用户
    int updateUser(User user);
    //删除用户
    int deleteUser(Integer id);

}

