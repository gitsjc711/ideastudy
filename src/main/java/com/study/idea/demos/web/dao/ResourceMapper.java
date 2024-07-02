package com.study.idea.demos.web.dao;

import com.study.idea.demos.web.entity.Progress;
import com.study.idea.demos.web.entity.Resource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ResourceMapper {
    @Select("select id,status,name,type,url,chapter_id as chapterId from resource where chapter_id=#{chapterId}")
    List<Resource> findByChapterId(int chapterId);
    @Insert("insert into resource(create_time, update_time, status, name, type, url, chapter_id) " +
                        "VALUES (#{createTime},#{updateTime},#{status},#{name},#{type},#{url},#{chapterId})")
    boolean insert(Resource resource);
    @Select("select id, status, user_id as userId, resource_id as resourceId from progress where user_id=#{userId} and resource_id=#{resourceId}")
    Progress findByUserIdAndResourceId(Progress progress);
    @Insert("insert into progress(create_time, update_time, status, user_id, resource_id) " +
    "values (#{createTime},#{updateTime},#{status},#{userId},#{resourceId})")
    boolean insertProgress(Progress progress);
}
