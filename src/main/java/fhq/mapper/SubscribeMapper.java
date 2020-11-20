package fhq.mapper;

import fhq.pojo.Subscribe;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeMapper {
    //添加收藏
    int subscribe(Subscribe subscribe);
    //查询收藏
    Subscribe selectSubscribe(Subscribe subscribe);
}
