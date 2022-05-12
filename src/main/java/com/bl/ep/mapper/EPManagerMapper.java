package com.bl.ep.mapper;

import com.bl.ep.bean.EPManager;
import com.bl.ep.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName EPManagerMapper
 * @Description 管理员 dao
 * @Author 陈宝梁
 * @Date 2021/11/15 18:15
 * @Version 1.0
 **/
@CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface EPManagerMapper {
    /**
     * 登录
     *
     * @param no
     * @return
     */
    EPManager selectByAccount(@Param("no") String no);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    EPManager selectById(@Param("id") Integer id);

}
