package fhq.service.serviceImpl;

import fhq.mapper.SubscribeMapper;
import fhq.pojo.Comic;
import fhq.pojo.Subscribe;
import fhq.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubscribeServiceImpl implements SubscribeService {
     @Autowired
     private SubscribeMapper subscribeMapper;
    @Override
    public Map<String ,Object> subscribe(Integer userID, Integer comicId) {
        //封装数据到subscribe类
        Subscribe subscribe=new Subscribe();
        subscribe.setUserId(userID);
        subscribe.setComicId(comicId);
        Map<String,Object> map=new HashMap<>();
            //调用方法
            int i=subscribeMapper.subscribe(subscribe);
            System.out.println("i="+i);
            if (i>0) {
                map.put("state", 200);
                map.put("msg", "收藏成功！");
            }


        return map;
    }
    //查询所有收藏
    @Override
    public List<Comic> selectAllSubscribe(Integer userId) {
        List<Comic> comics = subscribeMapper.selectAllSubscribe(userId);

        return comics;
    }
    //取消收藏
    public Map<String,Object> cancelSubscribe(Integer userId,Integer comicId){
        //封装数据到subscribe类
        Subscribe subscribe=new Subscribe();
        subscribe.setUserId(userId);
        subscribe.setComicId(comicId);
        Map<String,Object> map=new HashMap<>();
        int i = subscribeMapper.cancelSubscribe(subscribe);
        if (i>0){
            map.put("state",200);
            map.put("msg","取消收藏！");

        }
        return map;
    }
    //查询单个收藏
    public Map<String,Object> selectSubscribe(Integer userId,Integer comicId){
        Subscribe subscribe=new Subscribe();
        subscribe.setUserId(userId);
        subscribe.setComicId(comicId);
        Map<String,Object> map=new HashMap<>();
        //验证是否已收藏
        String result= subscribeMapper.selectSubscribe(subscribe);

        if (result==null){

            map.put("state",200);
            return map;
        }else {
            map.put("state",201);

            return map;
        }

    }
}
