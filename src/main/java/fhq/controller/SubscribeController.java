package fhq.controller;

import fhq.pojo.Comic;
import fhq.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/subscribe")
public class SubscribeController {
    @Autowired
     private SubscribeService subscribeService;
    //收藏功能
    @PostMapping("/subscribeComic")
    @ResponseBody
    public Map<String,Object> subscribe(HttpSession session,Integer comicId){
        Integer userId=(Integer) session.getAttribute("userId");
        System.out.println("userId="+userId);
        System.out.println("comicId="+comicId);
        Map<String, Object> map = subscribeService.subscribe(userId, comicId);
        return map;

    }
    //查询收藏
    @PostMapping("/selectAllSubscribe")
    @ResponseBody
    public List<Comic> selectAllSubscribe(HttpSession session){
        Integer userId=(Integer) session.getAttribute("userId");
        List<Comic> list = subscribeService.selectAllSubscribe(userId);
        return list;

    }
    //取消收藏
    @PostMapping("/cancelSubscribe")
    @ResponseBody
    public Map<String,Object> cancelSubscribe(HttpSession session,Integer comicId){
        Integer userId=(Integer) session.getAttribute("userId");
        System.out.println("userId="+userId);
        System.out.println("comicId="+comicId);
        Map<String, Object> map = subscribeService.cancelSubscribe(userId, comicId);
        return map;
    }
    //查询某一收藏
    @PostMapping("/selectSubscribe")
    @ResponseBody
    public Map<String,Object> selectSubscribe(HttpSession session,Integer comicId){
        Integer userId=(Integer) session.getAttribute("userId");
     
        Map<String, Object> map = subscribeService.selectSubscribe(userId, comicId);

        return map;

    }
}
