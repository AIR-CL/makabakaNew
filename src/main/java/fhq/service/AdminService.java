package fhq.service;

import fhq.pojo.User;

import java.util.List;
import java.util.Map;

public interface AdminService {
    //查询所有用户
     List<User> findAllUsersInfo();
    //用户模糊查询
     List<User> findUsersLikeInfo(Integer userId,String userName,Integer userVip,Integer loPh,Integer hiPh);
    //用户修改
     Map<String,Object> updateUserInfo(Integer userId,String userName,Integer userVip, Integer pushMoney);
     //根据id删除用户
    Map<String,Object> deleteUserInfo(Integer id);
}
