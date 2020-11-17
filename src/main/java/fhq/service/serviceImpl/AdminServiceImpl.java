package fhq.service.serviceImpl;

import fhq.mapper.AdminMapper;
import fhq.pojo.User;
import fhq.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    //查询所有用户
    public List<User> findAllUsersInfo() {
        List<User> users = adminMapper.findAllUsers();
        return users;
    }
    //用户模糊查询
    public List<User> findUsersLikeInfo(Integer userId,String userName,Integer userVip,Integer loPh,Integer hiPh) {
        //将数据封装成一个user
        User user=new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserVip(userVip);
        user.setLoPh(loPh);
        user.setHiPh(hiPh);

        //调用持久层的方法
        List<User> usersLike = adminMapper.findUsersLike(user);

        return usersLike;
    }
    //用户修改
    public Map<String,Object> updateUserInfo(Integer userId,String userName, Integer userVip, Integer pushMoney) {
        //数据封装
        User user=new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserVip(userVip);
        user.setPushMoney(pushMoney);
        System.out.println(user);
        int i = adminMapper.updateUser(user);
        Map<String,Object> map=new HashMap<String, Object>();
         if (i >0) {
             map.put("state",200);
            map.put("msg","修改成功");
            return map;
         }else {
             map.put("state",201);
             map.put("msg","修改失败");
             return map;
         }



    }

    public Map<String, Object> deleteUserInfo(Integer id) {

        Map<String,Object> map=new HashMap<String, Object>();
        int i = adminMapper.deleteUser(id);
        if (i > 0) {
            map.put("msg","删除成功");
            return map;
        }else {
            map.put("msg","删除失败");
            return map;
        }

    }
}
