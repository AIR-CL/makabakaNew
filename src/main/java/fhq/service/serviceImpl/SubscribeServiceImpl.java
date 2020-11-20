package fhq.service.serviceImpl;

import fhq.mapper.SubscribeMapper;
import fhq.pojo.Subscribe;
import fhq.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
        //验证是否已收藏
        Subscribe selectSubscribe = subscribeMapper.selectSubscribe(subscribe);

        if("".equals(selectSubscribe)){
            //调用方法
            int i=subscribeMapper.subscribe(subscribe);
            System.out.println("i="+i);
            if (i >0) {
                map.put("state",200);
                map.put("msg","收藏成功！");
            }else {
                map.put("state",201);
                map.put("msg","收藏失败！");
            }

        }else {
            map.put("msg","该番剧已被收藏！");
        }

        return map;
    }
}
