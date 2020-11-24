package fhq.mapper;

import fhq.pojo.Comic;
import fhq.pojo.Subscribe;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeMapper {
    //添加收藏
    int subscribe(Subscribe subscribe);
    //查询收藏
    String selectSubscribe(Subscribe subscribe);
    //查询所有收藏
    List<Comic> selectAllSubscribe(Integer userId);
    //取消收藏
    int cancelSubscribe(Subscribe subscribe);
}
