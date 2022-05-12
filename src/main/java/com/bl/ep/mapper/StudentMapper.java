package com.bl.ep.mapper;

import com.bl.ep.bean.Student;
import com.bl.ep.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName StudentMapper
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/15 17:42
 * @Version 1.0
 **/
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface StudentMapper {

    /**
     * @Method selectByPrimaryKey
     * @Author 陈宝梁
     * @Description 根据id 查询学生
     * @Date 2021/11/15 23:07
     **/
    Student selectByPrimaryKey(@Param("id") Long id);

    /**
     * @Method selectByNameAndNo
     * @Author 陈宝梁
     * @Description 根据学号和姓名查找学生
     * @Date 2021/11/15 23:08
     **/
    Student selectByNameAndNo(@Param("no") String no, @Param("username") String username);

    /**
     * @Method selectAll
     * @Author 陈宝梁
     * @Description 查找所有学生
     * @Date 2021/11/15 23:09
     **/
    List<Student> selectAll();

    /**
     * @Method selectByAccount
     * @Author 陈宝梁
     * @Description 根据学号 查找学生
     * @Date 2021/11/16 0:08
     **/
    Student selectByAccount(@Param("no") String no);

    /**
     * 修改密码
     * @param student
     */
    int updatePasswordAndId(Student student);

}
