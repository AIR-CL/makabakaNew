package fhq.mapper;

import fhq.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminMapper {

    //根据id查询
    User findUserById(Integer userId);
    //查询所有用户
    Integer findUsersCount(User user);
    //用户的模糊分页查询
    List<User> findUsersLikeByPage(User user);
    //普通分页查询
    List<User> findUsersByPage(Integer loLimit);
    //修改用户
    int updateUser(User user);
    //删除用户
    int deleteUser(Integer id);

}

