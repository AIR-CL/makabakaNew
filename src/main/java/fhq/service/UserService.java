package fhq.service;

import fhq.pojo.User;


import java.util.List;

public interface UserService {
    //查询所有用户
    public List<User> findAllUsersInfo();
}
