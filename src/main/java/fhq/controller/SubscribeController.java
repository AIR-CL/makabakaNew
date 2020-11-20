package fhq.controller;

import fhq.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/subscribe")
public class SubscribeController {
    @Autowired
     private SubscribeService subscribeService;
    //收藏功能
    @PostMapping("/subscribeComic")
    @ResponseBody
    public Map<String,Object> subscribe(HttpSession session){
        Integer userId=(Integer) session.getAttribute("userId");
        System.out.println("userId="+userId);
        Integer comicId=(Integer) session.getAttribute("comicId");
        System.out.println("comicId="+comicId);
        subscribeService.subscribe(userId,comicId);
        return null;

    }
    @PostMapping("/setComicId")
    @ResponseBody
    public Integer setComicID(Integer comicId, HttpSession session){
        session.setAttribute("comicId",comicId);
        System.out.println(comicId);
        Integer i=1;
        return  i;

    }

}
