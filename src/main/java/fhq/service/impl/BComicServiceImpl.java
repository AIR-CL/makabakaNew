package fhq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fhq.mapper.BComicMapper;
import fhq.pojo.Comic;
import fhq.service.BComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
