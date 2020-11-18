package fhq.service.serviceImpl;

import fhq.mapper.UserMapper;
import fhq.pojo.User;
import fhq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
//注入持久层对象
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> checkUserName(String username) {
      Map<String,Object> map=new HashMap<>();
      //对用户名进行判断
        if (username==null||"".equals(username)){
            map.put("state",0);
            map.put("msg","✘:用户名不能为空! ｡◕︿◕｡");
            return map;
        }else{
            //根据用户名查找用户信息
            User user = userMapper.findUserByUserName(username);

            //判断user
            if (user==null){
                map.put("state",1);
                map.put("msg","✔:用户名可用｡◕‿◕｡");
            }else {
                map.put("state",0);
                map.put("msg","✘:用户名已被注册! ｡◕︿◕｡");
            }
            return map;
        }

    }

    @Override
    public Map<String, Object> toReg(String username, String password) {

        Map<String,Object> map=new HashMap<>();
        //对参数进行判断
        if (username==null||"".equals(username)){
            map.put("state",100);
            map.put("msg","用户名不能为空! (︶︿︶)");
            return  map;
        }
        if (password==null||"".equals(password)){
            map.put("state",100);
            map.put("msg","密码不能为空! (︶︿︶)");
            return  map;
        }
        //根据用户名查询当前用户是否存在
        User u = userMapper.findUserByUserName(username);
        if (u!=null){
            map.put("state",100);
            map.put("msg","该用户已经存在! (︶︿︶)");
            return map;
        }else {
            //执行注册
            //使用UUID生成唯一的字符串,作为盐值
            //密码加密 MD5加密
            String salt = UUID.randomUUID().toString();
            //加密后的密码
            String md5Password = getMD5Password(password, salt);

            User user=new User();
            user.setUserName(username);
            user.setUserPassword(md5Password);
            user.setSalt(salt);


            //调用持久层对象
            int row = userMapper.regUser(user);
            if (row>=1){
                map.put("state",200);
                map.put("msg","注册成功!!! ╮(￣▽ ￣)╭");
            }else {
                map.put("state",100);
                map.put("msg","注册失败! (×_×)");
            }
            return  map;
        }

    }

    @Override
    public Map<String, Object> toLog(String username, String password, HttpServletRequest req) {
        Map<String,Object> map=new HashMap<>();
        //对参数进行判断
        if (username==null||"".equals(username)){
            map.put("state",100);
            map.put("msg","用户名不能为空! (︶︿︶)");
            return  map;
        }
        if (password==null||"".equals(password)){
            map.put("state",100);
            map.put("msg","密码不能为空! (︶︿︶)");
            return  map;
        }
        //根据用户名查询当前用户是否存在
        User u = userMapper.findUserByUserName(username);
        if (u==null){
            map.put("state",100);
            map.put("msg","请先注册! (︶︿︶)");
            return map;
        }
            //获取用户的盐值
            String salt = u.getSalt();
            //加密后的密码
            String md5Password = getMD5Password(password, salt);
            User user = userMapper.loginCheck(username, md5Password);
            if (user!=null){
                map.put("username",username);
                map.put("state",200);
                map.put("msg","登陆成功!!! ╮(￣▽ ￣)╭ ");
                //将用户名绑定到session中
                req.getSession().setAttribute("username",user.getUserName());
                req.getSession().setAttribute("salt",user.getSalt());
                return map;
            }else{
                map.put("state",100);
                map.put("msg","用户名或密码错误! (︶︿︶)");
                return map;
            }



        }





    //封装一个密码加密的方法
    private String getMD5Password(String password,String salt){
        String md5password=password+salt;
        //可以使用循环方式 10遍,取最后一次的密码作为加密最终密码
        md5password= DigestUtils.md5DigestAsHex(md5password.getBytes());
        return md5password;
    }

}
