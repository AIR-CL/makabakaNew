package fhq.service.impl;

import fhq.mapper.UserMapper;
import fhq.pojo.User;
import fhq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;


    public List<User> findAllUsersInfo() {
        List<User> users = userMapper.findAllUsers();

        return users;
    }
}
