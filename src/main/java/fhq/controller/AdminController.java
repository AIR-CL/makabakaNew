package fhq.controller;


import fhq.pojo.User;
import fhq.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //到管理员页面
    @GetMapping("/toIndex")
    public String toIndex(){
        return "controlHtml/index";
    }
    //根据id查询
    @PostMapping("/findUserById")
    @ResponseBody
    public User findUserById(Integer userId){
        User user = adminService.findUserByIdInfo(userId);
        return user;
    }


    //查询总记录数
    @GetMapping("/findUsersCount")
    @ResponseBody
    public Integer findUsersCount(){
        //调用业务层的查询方法
        Integer totalPage = adminService.findTotalPage();
        return totalPage;
    }
    //模糊查询总记录数
    @PostMapping("/findUsersLikeCount")
    @ResponseBody
    public Integer findUsersLikeCount(Integer userId,String userName,Integer userVip,Integer loPh,Integer hiPh){

        Integer totalPage = adminService.findUsersCountInfo(userId, userName, userVip, loPh, hiPh);

        return totalPage;
    }
    //模糊查询分页
    @PostMapping("/findUsersLikeByPage")
    @ResponseBody
    public List<User> findUsersLike(Integer userId,String userName,Integer userVip,Integer loPh,Integer hiPh,Integer currentPage,HttpSession session){
        //设置当前页面，方便进行下一页上一页操作
        session.setAttribute("currentPage",currentPage);
        List<User> users = adminService.findUsersLikeByPageInfo(userId, userName, userVip, loPh,hiPh,currentPage);
        return users;
    }
    //分页查询
    @PostMapping("/findUsersByPage")
    @ResponseBody
    public List<User> findUsersByPage(Integer currentPage,HttpSession session){
        //设置当前页面，方便进行下一页上一页操作
        session.setAttribute("currentPage",currentPage);
        List<User> users = adminService.findUsersByPageInfo(currentPage);
        return users;
    }




    //用户修改
   @PostMapping("/updateUser")
    @ResponseBody
    public Map<String,Object> updateUser(String userName,Integer userVip,Integer pushMoney,Integer userId){
       Map<String, Object> map = adminService.updateUserInfo(userId, userName, userVip, pushMoney);
       return map;
   }
    //用户删除
    @PostMapping("/deleteUser")
    @ResponseBody
    public  Map<String,Object> deleteUser(Integer id){

        Map<String, Object> map = adminService.deleteUserInfo(id);
        return map;
    }

    //跳转到修改页面
    @GetMapping("/toUpdateUserPage")
    public String toUpdatePage(Integer userId, HttpSession session){
        //设定一个seesion用于发送ajax请求
        session.setAttribute("userId",userId);
        return "/controlHtml/updatePage";

    }
    //获取session
    @GetMapping("/getSession")
    @ResponseBody
    public Map<String,Object> getSession(HttpSession session){
        //获取当前页
        Integer currentPage=(Integer) session.getAttribute("currentPage");
        //获取总页数
        Integer totalPage = adminService.findTotalPage();
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("currentPage",currentPage);
        map.put("totalPage",totalPage);

        return map;
    }

    //模糊getSession
    @GetMapping("/getLikeSession")
    @ResponseBody
    public Map<String,Object> getLikeSession(Integer userId,String userName,Integer userVip,Integer loPh,Integer hiPh,HttpSession session){
        //获取当前页面
        Integer currentPage=(Integer) session.getAttribute("currentPage");
        //获取总页数
        Integer totalPage = adminService.findUsersCountInfo(userId,userName,userVip,loPh,hiPh);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("currentPage",currentPage);
        map.put("totalPage",totalPage);

        return map;
    }

}
