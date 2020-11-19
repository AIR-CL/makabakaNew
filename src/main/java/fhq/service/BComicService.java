package fhq.service;

import com.github.pagehelper.PageInfo;
import fhq.pojo.Comic;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface BComicService {
    //分页查询
    PageInfo<Comic> findALLComic(int page, int pageSize,String sInfo);

    //删除一条
    Map<String,Object> delComic(Integer comicId);

    //新增一条
    Map<String,Object> addComic(MultipartFile file, Comic comic, HttpServletRequest request);

    //根据id查询一条数据
    Comic findComicById(Integer comicId,HttpServletRequest request);

    //根据动漫id修改番剧信息
    Map<String,Object> updateComic(Comic comic,MultipartFile file,HttpServletRequest request);
}
