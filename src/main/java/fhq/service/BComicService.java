package fhq.service;

import com.github.pagehelper.PageInfo;
import fhq.pojo.Comic;

import java.util.List;
import java.util.Map;


public interface BComicService {
    //分页查询
    PageInfo<Comic> findALLComic(int page, int pageSize,String sInfo);

    //删除一条
    Map<String,Object> delComic(Integer comicId);
}
