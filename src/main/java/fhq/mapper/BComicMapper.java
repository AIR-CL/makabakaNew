package fhq.mapper;

import fhq.pojo.Comic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BComicMapper {
    //查询所有
    List<Comic> findAllComic(@Param("sInfo") String sInfo);

    //删除一条
    int delComic(Integer comicId);
}
