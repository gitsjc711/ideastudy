package com.study.idea.demos.web.dao;

import com.study.idea.demos.web.entity.Resource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ResourceMapper {
    @Select("select id,status,name,type,url,chapter_id as chapterId from resource where chapter_id=#{chapterId}")
    List<Resource> findByChapterId(int chapterId);
    @Select("select id,status,name,type,url,chapter_id as chapterId from resource where url=#{url}")
    Resource findByUrl(String url);
    @Insert("insert into resource(create_time, update_time, status, name, type, url, chapter_id) " +
                        "VALUES (#{createTime},#{updateTime},#{status},#{name},#{type},#{url},#{chapterId})")
    boolean insert(Resource resource);
}
