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
    //查询模糊查询总页数
    public Integer findUsersCountInfo(Integer userId, String userName, Integer userVip, Integer loPh, Integer hiPh) {
        User user=new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserVip(userVip);
        user.setLoPh(loPh);
        user.setHiPh(hiPh);
        //返回记录数
        Integer count = adminMapper.findUsersCount(user);
        Integer totalPage;
        if (count%5!=0){
            totalPage=count/5+1;
        }else {
            totalPage=count/5 ;
        }
        return totalPage;
    }
    //普通查询总页数
    public Integer findTotalPage() {
        User user=new User();
        Integer count = adminMapper.findUsersCount(user);
        Integer totalPage;
        if (count%5!=0){
            totalPage=count/5+1;
        }else {
            totalPage=count/5 ;
        }
        return totalPage;
    }

    //模糊分页查询
    public List<User> findUsersLikeByPageInfo(Integer userId,String userName,Integer userVip,Integer loPh,Integer hiPh,Integer currentPage) {
        //将数据封装成一个user
        User user=new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserVip(userVip);
        user.setLoPh(loPh);
        user.setHiPh(hiPh);
        //总页数
        Integer totalPage;
        //查询模糊搜索的总记录条数
        Integer count = adminMapper.findUsersCount(user);
        if(count%5!= 0){
            totalPage=count/5+1;
        }else {
            totalPage=count/5;
        }

        if (currentPage > totalPage) {
            currentPage=totalPage;
        }
        int loLimit=5*(currentPage-1);
        user.setLoLimit(loLimit);
        List<User> usersLike = adminMapper.findUsersLikeByPage(user);

        return usersLike;
    }
    //分页查询
    public List<User> findUsersByPageInfo(Integer currentPage) {
        //将数据封装成一个user
        User user=new User();
        //设置起点
        if(currentPage-1<0){
            currentPage=1;
        }
        //总页数
        Integer totalPage;
        Integer count = adminMapper.findUsersCount(user);
        if(count%5!= 0){
        totalPage=count/5+1;
        }else {
            totalPage=count/5;
        }

        if (currentPage > totalPage) {
            currentPage=totalPage;
        }
        int loLimit=5*(currentPage-1);


        List<User> usersLike = adminMapper.findUsersByPage(loLimit);

        return usersLike;
    }





    //用户修改实现
    public Map<String,Object> updateUserInfo(Integer userId,String userName, Integer userVip, Integer pushMoney) {
        //数据封装
        User user=new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserVip(userVip);
        user.setPushMoney(pushMoney);
       //调用接口
        int i = adminMapper.updateUser(user);
        //设定返回信息
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
    //删除用户实现
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
    //根据id查询用户
    public User findUserByIdInfo(Integer userId) {
        User user = adminMapper.findUserById(userId);
        return user;
    }
}
