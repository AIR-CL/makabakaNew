package fhq.service;

import fhq.pojo.Comic;

import java.util.List;
import java.util.Map;

public interface SubscribeService {
    //收藏
    Map<String,Object> subscribe(Integer userID, Integer comicId);
    //查询收藏
    List<Comic> selectAllSubscribe(Integer userId);
    //删除收藏
    Map<String,Object> cancelSubscribe(Integer userId,Integer comicId);
    //查询单个收藏
    Map<String,Object> selectSubscribe(Integer userId,Integer comicId);
}
