package com.bl.ep.mapper;

import com.bl.ep.bean.HealthCode;
import com.bl.ep.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName HealthCodeMapper
 * @Description 健康码 dao
 * @Author 陈宝梁
 * @Date 2021/11/15 18:16
 * @Version 1.0
 **/
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface HealthCodeMapper {
    /**
     * @Method insertBySelective
     * @Author 陈宝梁
     * @Description
     * @Date 2021/11/15
     **/
    int insertBySelective(HealthCode healthCode);

    /**
     * @Method selectByPrimaryKey
     * @Author 陈宝梁
     * @Description 根据 id 查询健康码
     * @Date 2021/11/15
     **/
    HealthCode selectByPrimaryKey(@Param("id") String id);

    /**
     * @Method selectByStudent
     * @Author 陈宝梁
     * @Description 查询该学生的健康码信息
     * @Date 2021/11/16 17:21
     **/
    HealthCode selectByStudent(@Param("sid") Long id);

    /**
     * @Method selectAll
     * @Author 陈宝梁
     * @Description 查询所有
     * @Date 2021/11/22 12:54
     **/
    List<HealthCode> selectAll();

    /**
     * @Method updateByHealthCode
     * @Author 陈宝梁
     * @Description 修改
     * @Date 2021/11/15
     **/
    int updateByHealthCode(HealthCode healthCode);

    /**
     * @Method selectByStudentAndNo
     * @Author 陈宝梁
     * @Description  根据学号查询学生
     * @Date 2021/11/25 10:51
     **/
    HealthCode selectByStudentAndNo(@Param("sno") String sno);

}
