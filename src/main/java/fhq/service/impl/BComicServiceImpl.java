package fhq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fhq.mapper.BComicMapper;
import fhq.pojo.Comic;
import fhq.service.BComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class BComicServiceImpl implements BComicService {
    @Autowired
    private BComicMapper bComicMapper;
    @Override
    public PageInfo<Comic> findALLComic(int page,int pageSize,String sInfo) {
        /*if (page<=0){
            page=1;
        }*/
        //开启分页支持
        PageHelper.startPage(page,pageSize);
        //调用dao查询所有
        List<Comic> comics = bComicMapper.findAllComic(sInfo);
        //获取分页相关信息
        PageInfo<Comic> pageInfo = new PageInfo<>(comics);
        /*if (pageInfo.getPageNum()>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }*/
        /*System.out.println("获取当前页："+pageInfo.getPageNum());
        System.out.println("获取总页数："+pageInfo.getPages());
        System.out.println("每页条数："+pageInfo.getPageSize());
        System.out.println("总记录数："+pageInfo.getTotal());
        System.out.println("获取第一页："+pageInfo.getNavigateFirstPage());
        System.out.println("获取最后一页："+pageInfo.getNavigateLastPage());
        System.out.println("上一页："+pageInfo.getPrePage());
        System.out.println("下一页："+pageInfo.getNextPage());
        System.out.println("当前页的数据："+pageInfo.getList());*/

        return pageInfo;
    }

    @Override
    public Map<String, Object> delComic(Integer comicId) {
        Map<String,Object> map = new HashMap<>();
        int row = bComicMapper.delComic(comicId);
        if (row >= 1){
            map.put("state",200);
            map.put("msg","删除成功");
        }else {
            map.put("state",201);
            map.put("msg","网络连接超时请稍后再试");
        }
        return map;
    }

    @Override
    public Map<String, Object> addComic(MultipartFile file, Comic comic, HttpServletRequest request) {

        Map<String,Object> map = new HashMap<>();
        if (file == null){
            map.put("msg","请选择上传");
            return map;
        }
        if (file.getSize()>52428800){
            map.put("msg","文件大小超出50M");
            return map;
        }

        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File f = new File(path);
        if (!f.exists()){
            f.mkdirs();
        }

        String filename = file.getOriginalFilename();
        int startIndex = filename.lastIndexOf(".");
        //得到文件的后缀
        String subffix = filename.substring(startIndex);
        //使用时间 UUID 随机数
        String preffix = UUID.randomUUID().toString();
        //新的文件名
        String newfilename = preffix+subffix;
        String comicFace = "/uploads/"+newfilename;
        comic.setComicFace(comicFace);

        try {
            file.transferTo(new File(path,newfilename));
            int row = bComicMapper.addComic(comic);

            if (row >= 1) {
                map.put("state",200);
                map.put("msg","上传成功");
                map.put("path",comicFace);
            }else {
                map.put("state",201);
                map.put("msg","上传失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            map.put("state",202);
            map.put("msg","上传失败");
        }
        return map;

    }

    @Override
    public Comic findComicById(Integer comicId) {
        Comic comic = bComicMapper.findComicById(comicId);
        return comic;
    }

    @Override
    public Map<String, Object> updateComic(Comic comic, MultipartFile file, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        if (file == null){
            map.put("msg","番剧封面未加载请重新选择");
            return map;
        }
        if (file.getSize()>52428800){
            map.put("msg","文件大小超出50M");
            return map;
        }

        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File f = new File(path);
        if (!f.exists()){
            f.mkdirs();
        }

        String filename = file.getOriginalFilename();
        int startIndex = filename.lastIndexOf(".");
        //得到文件的后缀
        String subffix = filename.substring(startIndex);
        //使用时间 UUID 随机数
        String preffix = UUID.randomUUID().toString();
        //新的文件名
        String newfilename = preffix+subffix;
        String comicFace = "/uploads/"+newfilename;
        comic.setComicFace(comicFace);

        try {
            file.transferTo(new File(path,newfilename));
            int row = bComicMapper.updateComic(comic);

            if (row >= 1) {
                map.put("state",200);
                map.put("msg","更新成功");
                map.put("path",comicFace);
            }else {
                map.put("state",201);
                map.put("msg","更新失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            map.put("state",202);
            map.put("msg","更新失败");
        }
        return map;
    }

    @Override
    public List<Comic> findAllComicInfo() {
        List<Comic> list = bComicMapper.findAllComicInfo();
        return list;
    }

}
