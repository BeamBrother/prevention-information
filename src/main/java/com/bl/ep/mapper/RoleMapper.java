package com.bl.ep.mapper;

import com.bl.ep.bean.Role;
import com.bl.ep.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName RoleMapper
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/15 23:32
 * @Version 1.0
 **/
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface RoleMapper {
    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    Role selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 根据 角色查询
     * @param role
     * @return
     */
    Role selectByRoleStr(@Param("role") String role);

    /**
     * 查询所有
     * @return
     */
    List<Role> selectAll();
}
