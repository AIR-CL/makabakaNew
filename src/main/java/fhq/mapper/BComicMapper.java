package fhq.mapper;

import fhq.pojo.Comic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BComicMapper {
    //查询所有
    List<Comic> findAllComic(@Param("sInfo") String sInfo);

    //删除一条
    int delComic(Integer comicId);

    //增加一条
    int addComic(Comic comic);

    //根据id查询番剧信息
    Comic findComicById(Integer comicId);

    //根据id修改
    int updateComic(Comic comic);


}
