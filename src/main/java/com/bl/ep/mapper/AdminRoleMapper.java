package com.bl.ep.mapper;

import com.bl.ep.bean.Role;
import com.bl.ep.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @ClassName AdminRoleMapper
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/15 21:31
 * @Version 1.0
 **/
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface AdminRoleMapper extends RoleMapper {
    /**
     * 插入新的角色
     * @param role
     * @return
     */
    int insertSelective(Role role);

    /**
     * 修改
     * @param role
     * @return
     */
    int updateSelective(Role role);
}
