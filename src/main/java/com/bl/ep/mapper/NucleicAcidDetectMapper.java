package com.bl.ep.mapper;

import com.bl.ep.bean.NucleicAcidDetect;
import com.bl.ep.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName NucleicAcidDetectMapper
 * @Description 核酸检测信息 dao
 * @Author 陈宝梁
 * @Date 2021/11/15 18:18
 * @Version 1.0
 **/
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface NucleicAcidDetectMapper {
    /**
     * @Method insertSelective
     * @Author 陈宝梁
     * @Description 检测信息录入
     * @Date 2021/11/15 21:53
     **/
    int insertSelective(NucleicAcidDetect nucleicAcidDetect);

    /**
     * @Method selectByUserId
     * @Author 陈宝梁
     * @Description 根据用户 学号查询其所有的检测信息
     * @Date 2021/11/15 21:54
     **/
    List<NucleicAcidDetect> selectByUserNo(@Param("no") String id);

    /**
     * @Method selectByPrimaryKey
     * @Author 陈宝梁
     * @Description 根据 检测信息 id查询检测信息
     * @Date 2021/11/22 20:14
     **/
    NucleicAcidDetect selectByPrimaryKey(@Param("id") String id);

    /**
     * @Method selectAll
     * @Author 陈宝梁
     * @Description 查询所有
     * @Date 2021/11/22 18:17
     **/
    List<NucleicAcidDetect> selectAll();
}
