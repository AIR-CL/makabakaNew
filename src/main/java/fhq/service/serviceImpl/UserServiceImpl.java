package fhq.service.serviceImpl;

import fhq.mapper.UserMapper;
import fhq.pojo.Advice;
import fhq.pojo.User;
import fhq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
//注入持久层对象
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> checkUserName(String username) {
      Map<String,Object> map=new HashMap<String, Object>();
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

        Map<String,Object> map=new HashMap<String, Object>();
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
        Map<String,Object> map=new HashMap<String, Object>();
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
                req.getSession().setAttribute("userId",user.getUserId());
                req.getSession().setAttribute("username",user.getUserName());
                req.getSession().setAttribute("salt",user.getSalt());
                return map;
            }else{
                map.put("state",100);
                map.put("msg","用户名或密码错误! (︶︿︶)");
                return map;
            }
        }

    @Override
    public Map<String, Object> toUpdatePassword(String oldPassword, String newPassword,
                                                String confirmPassword,HttpServletRequest request) {
        Map<String,Object> map=new HashMap<>();
        //非空判断
        if (oldPassword==null||"".equals(oldPassword)){
            map.put("state",100);
            map.put("msg","原密码不能为空");
            return map;
        }
        if (newPassword==null||"".equals(newPassword)){
            map.put("state",100);
            map.put("msg","新密码不能为空");
            return map;
        }
        if (confirmPassword==null||"".equals(confirmPassword)){
            map.put("state",100);
            map.put("msg","确认密码不能为空");
            return map;
        }
        //通过用户名查找用户
      String username = (String)request.getSession().getAttribute("username");

        if (username==null){
            map.put("state",200);
            map.put("msg","用户名过期,请重新登录");
            return map;
        }
        User user = userMapper.findUserByUserName(username);
        //获取用户信息的盐值
        String salt = user.getSalt();
        //使用原密码和盐值进行加密
        String md5Password = getMD5Password(oldPassword, salt);
        //比对密码
        if (!md5Password.equals(user.getUserPassword())){
            map.put("state",100);
            map.put("msg","原密码不一致");
            return map;
        }
        //比较新密码和确认密码
        if (!newPassword.equals(confirmPassword)){
            map.put("state",100);
            map.put("msg","密码不一致");
            return map;
        }
        //给新密码加密
        String newSalt = UUID.randomUUID().toString();
        String md5PasswordNew = getMD5Password(newPassword, newSalt);

        //使用持久层对象调用持久层方法
        int row = userMapper.toUpdatePassword(md5PasswordNew,username,newSalt);
        if (row>=1){
            map.put("state",200);
            map.put("msg","密码修改成功");
            return map;
        }else{
            map.put("state",100);
            map.put("msg","服务器异常,密码修改失败");
            return map;
        }
    }

    @Override
    public User findUserInfoByUsername(String username) {
        User user = userMapper.findUserInfoByUsername(username);
        return user;
    }

    @Override
    public Map<String, Object> modifyUserByName(String userName, String address, String tel, String sex,
                                                String birthday,String edu,String signature,String industry,String email) {

        Map<String,Object> map=new HashMap<>();
        if (birthday==""){
        birthday="1970-01-01";
        }
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = ft.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user=new User();
        user.setUserName(userName);
        user.setAddress(address);
        user.setTel(tel);
        user.setSex(sex);
        user.setBirthday(date);
        user.setEdu(edu);
        user.setSignature(signature);
        user.setIndustry(industry);
        user.setEmail(email);
        if ("".equals(user.getUserName())){
            map.put("state",201);
            map.put("msg","用户名已过期,请重新登录");
            return map;
        }
        int row = userMapper.modifyUserInfoByUsername(user);
        if (row>=1){
            map.put("state",200);
            map.put("msg","修改成功");
            return map;
        }else{
            map.put("state",100);
            map.put("msg","修改失败");
            return map;
        }

    }

    @Override
    public Map<String, Object> uploadFace(MultipartFile file, HttpServletRequest request) {
        Map<String,Object> map=new HashMap<>();
        //参数判断
        if (file==null||"".equals(file)){
            map.put("msg","请选择上传文件");
            return map;
        }
        //判断文件大小
        if (file.getSize()>52428800){
            map.put("state",100);
            map.put("msg","文件大小超出50M");
            return map;
        }
        //获取用户名
        String username =(String) request.getSession().getAttribute("username");
        System.out.println(username+12131);
        if ("".equals(username)||username==null){
            map.put("state",201);
            map.put("msg","用户名已过期,请重新登录");
            return map;
        }
//        //获取上传文件的原名
//        String filename = file.getOriginalFilename();
//        //从后往前找文件名的第一个"."
//      int start = filename.lastIndexOf(".");
        //得到文件的后缀
        String subffix = ".jpg";
                //filename.substring(start);
        //使用UUID产生文件名前缀
        String preffix = UUID.randomUUID().toString();
        //新的文件名
        String newFileName=preffix+subffix;
        //文件存储在哪里
        String path = request.getSession().getServletContext().getRealPath("face");//获取项目根目录
        File f=new File(path);
        if (!f.exists()){
            f.mkdir();//将文件夹创建出来
        }
        //
        File filePath=new File(path,newFileName);

        try {   //实现上传到服务器
            file.transferTo(filePath);

            //头像路径
            String face="/face/"+newFileName;

            int row = userMapper.uploadFace(username, face);
            if (row>=1){
                map.put("state","200");
                map.put("msg","上传成功");
                map.put("face",face);
                request.getSession().setAttribute("face",face);
                return map;
            }else {
                map.put("state","100");
                map.put("msg","上传失败,请重试");
                return map;
            }


        } catch (IOException e) {
            e.printStackTrace();
            map.put("state","100");
            map.put("msg","上传失败");
            return map;
        }

    }
    //提交留言
    @Override
    public Map<String, Object> submitAdvice(Integer userId, String adviceType, String adviceTitle, String adviceContent) {
        Advice advice=new Advice();
        advice.setUserId(userId);
        advice.setAdviceType(adviceType);
        advice.setAdviceTitle(adviceTitle);
        advice.setAdviceContent(adviceContent);
        Map<String,Object> map=new HashMap<>();
        System.out.println(advice);
        int i = userMapper.submitAdvice(advice);
        System.out.println("i==="+i);
        if(i==1){
            map.put("msg","留言成功");
        }else {
            map.put("msg","留言失败");
        }
        return map;
    }
    //查询留言
    @Override
    public List<Advice> selectAdvice(String adviceType) {
        List<Advice> advice = userMapper.selectAdvice(adviceType);
        return advice;
    }


    //封装一个密码加密的方法
    private String getMD5Password(String password,String salt){
        String md5password=password+salt;
        //可以使用循环方式 10遍,取最后一次的密码作为加密最终密码
        md5password= DigestUtils.md5DigestAsHex(md5password.getBytes());
        return md5password;
    }

}
