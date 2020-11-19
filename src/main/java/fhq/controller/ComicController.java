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

    //跳转到详情页
    @GetMapping("/toBroadcastPage")
    public String toBroadcastPage(){
        return "/web/broadcast";
    }
    //分页查询所有
    @GetMapping("/findAllComic")
    public @ResponseBody PageInfo<Comic> findAllComic(@RequestParam(value = "page",defaultValue = "1") int page,int pageSize,String sInfo){
        System.out.println("sInfo:"+sInfo);
        PageInfo<Comic> pageInfo = bComicService.findALLComic(page, pageSize,sInfo);

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


    
}
