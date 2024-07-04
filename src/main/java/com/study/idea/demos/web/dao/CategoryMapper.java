package com.study.idea.demos.web.dao;

import com.study.idea.demos.web.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select id,status,category_name as categoryName,parent_id as parentId from category")
    List<Category> findAll();
    @Select("select id,status,category_name as categoryName,parent_id as parentId from category where category_name=#{categoryName}")
    Category findByName(Category category);
    @Select("select id,status,category_name as categoryName,parent_id as parentId from category where id=#{id}")
    Category findById(int id);
    @Insert("insert into category(create_time, update_time, status, category_name, parent_id) " +
            "VALUES (#{createTime},#{updateTime},#{status},#{categoryName},#{parentId})")
    boolean insert(Category category);
    @Delete("delete from category where id=#{id}")
    boolean delete(int id);



}
