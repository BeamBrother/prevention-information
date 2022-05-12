package com.bl.ep.mapper;

import com.bl.ep.bean.HealthReportInfo;
import com.bl.ep.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @ClassName HealthReportInfo
 * @Description 签到 dao
 * @Author 陈宝梁
 * @Date 2021/11/15 18:17
 * @Version 1.0
 **/
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface HealthReportInfoMapper {
    /**
     * @Method insertSelective
     * @Author 陈宝梁
     * @Description 签到
     * @Date 2021/11/15
     **/
    int insertSelective(HealthReportInfo healthReportInfo);

    /**
     * @Method selectAll
     * @Author 陈宝梁
     * @Description 查询所有
     * @Date 2021/11/15 21:13
     **/
    List<HealthReportInfo> selectAll(@Param("reportTime") Date reportTime);
}
