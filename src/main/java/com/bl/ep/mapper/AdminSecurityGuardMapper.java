package com.bl.ep.mapper;

import com.bl.ep.bean.SecurityGuard;
import com.bl.ep.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @ClassName AdminSecurityGuardMapper
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/15 21:29
 * @Version 1.0
 **/
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface AdminSecurityGuardMapper extends SecurityGuardMapper {
    /**
     * @Method insertSelective
     * @Author 陈宝梁
     * @Description 插入数据
     * @Date 2021/11/16 0:12
     **/
    int insertSelective(SecurityGuard securityGuard);
}
