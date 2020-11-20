package fhq.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fhq.pojo.Comic;
import fhq.pojo.PageBean;
import fhq.service.BComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comic")
public class ComicController {
    @Autowired
    private BComicService bComicService;


    //跳转到主页
    @RequestMapping("/toIndex")
    public String toIndex2(){
        return "/controlHtml/index2";
    }

    //跳转到增添番剧页面
    @GetMapping("/addComicPage")
    public String addComicPage(){
        return "/controlHtml/index2_add";
    }

    //跳转到修改页面
    @GetMapping("/updatePage")
    public String updatePage(){
        return "/controlHtml/index2_update";
    }
    //跳转到详情页面
    @GetMapping("/toBroadcastPage")
    public String toBroadcastPage(){
        return "/web/broadcast";
    }
    //分页查询所有
    @GetMapping("/findAllComic")
    public @ResponseBody PageInfo<Comic> findAllComic(@RequestParam(value = "page",defaultValue = "1") int page,int pageSize,String sInfo,HttpServletRequest request){
        System.out.println("sInfo:"+sInfo);
        PageInfo<Comic> pageInfo = bComicService.findALLComic(page, pageSize,sInfo);
        request.getSession().setAttribute("sInfo",sInfo);
        /*PageBean<Comic> pageBean = new PageBean<>();
        pageBean.setList(pageInfo.getList());
        pageBean.setTotalCount(pageInfo.getPages());
        pageBean.setTotalCount(pageInfo.getTotal());
        pageBean.setCurrentPage(pageInfo.getPageNum());
        pageBean.setRows(pageInfo.getPageSize());*/

        return pageInfo;
    }

    //删除条目
    @GetMapping("/delComic")
    public @ResponseBody Map<String,Object> delComic(Integer comicId){
        Map<String, Object> map = bComicService.delComic(comicId);
        return map;
    }

    //新番创建
    @PostMapping("/addComic")
    public @ResponseBody Map<String,Object> addComic(MultipartFile comicFace,String comicName,String broadcastTime,String dubbing,String comicType,String comicState, HttpServletRequest request){
        Comic comic = new Comic();
        comic.setComicName(comicName);
        comic.setBroadcastTime(broadcastTime);
        comic.setComicState(comicState);
        comic.setDubbing(dubbing);
        comic.setComicType(comicType);
        Map<String, Object> map = bComicService.addComic(comicFace, comic, request);

        return map;
    }

    //根据番剧id查询出一条番剧信息
    @GetMapping("/findComicById")
    public @ResponseBody Comic findComicById(Integer comicId){
        Comic comic = bComicService.findComicById(comicId);
        return comic;
    }
    //更新番剧信息
    @PostMapping("/updateComic")
    public @ResponseBody Map<String,Object> updateComic(Integer comicId,MultipartFile comicFace,String comicName,String broadcastTime,String dubbing,String comicType,String comicState, HttpServletRequest request){
        Comic comic = new Comic();
        comic.setComicId(comicId);
        comic.setComicName(comicName);
        comic.setBroadcastTime(broadcastTime);
        comic.setComicState(comicState);
        comic.setDubbing(dubbing);
        comic.setComicType(comicType);
        System.out.println(comicFace.getOriginalFilename());
        Map<String, Object> map = bComicService.updateComic(comic, comicFace, request);
        return map;
    }
    //查询所有番剧
    @GetMapping("/findAllComicToView")
    @ResponseBody
    public List<Comic> findAllComicINfo(){
        List<Comic> list = bComicService.findAllComicInfo();
        System.out.println(list);
        return list;
    }

}
