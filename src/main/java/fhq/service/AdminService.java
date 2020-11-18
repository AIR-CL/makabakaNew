package fhq.service;

import fhq.pojo.User;

import java.util.List;
import java.util.Map;

public interface AdminService {
    //查询用户数量
     Integer findUsersCountInfo(Integer userId,String userName,Integer userVip,Integer loPh,Integer hiPh);
     //总记录数
     Integer findTotalPage();
     //用户模糊查询
     List<User> findUsersLikeByPageInfo(Integer userId,String userName,Integer userVip,Integer loPh,Integer hiPh,Integer currentPage);

     List<User> findUsersByPageInfo(Integer currentPage);
    //用户修改
     Map<String,Object> updateUserInfo(Integer userId,String userName,Integer userVip, Integer pushMoney);
     //根据id删除用户
    Map<String,Object> deleteUserInfo(Integer id);

    //根据id查询用户
    User findUserByIdInfo(Integer userId);
}
