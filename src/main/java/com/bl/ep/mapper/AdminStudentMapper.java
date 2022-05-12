package com.bl.ep.mapper;

import com.bl.ep.bean.Student;
import com.bl.ep.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @ClassName AdminStudentMapper
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/15 21:23
 * @Version 1.0
 **/
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface AdminStudentMapper extends StudentMapper {
    /**
     * @Method insertSelective
     * @Author 陈宝梁
     * @Description 插入数据
     * @Date 2021/11/15 23:07
     **/
    int insertSelective(Student student);
}
