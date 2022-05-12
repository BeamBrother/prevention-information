package com.bl.ep.mapper;

import com.bl.ep.bean.SecurityGuard;
import com.bl.ep.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SecurityGuardMapper
 * @Description 门岗检测员 dao
 * @Author 陈宝梁
 * @Date 2021/11/15 18:13
 * @Version 1.0
 **/
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface SecurityGuardMapper {
    /**
     * @Method selectByAccount
     * @Author 陈宝梁
     * @Description 根据工号和密码查找核酸检测人员
     * @Date 2021/11/16 0:12
     **/
    SecurityGuard selectByAccount(@Param("no") String no);

    /**
     * @Method selectByPrimaryId
     * @Author 陈宝梁
     * @Description 根据 id 查询 门岗检测员
     * @Date 2021/11/16 17:14
     **/
    SecurityGuard selectByPrimaryKey(@Param("id") Long id);

    /**
     * 修改面膜
     * @param securityGuard
     */
    int updatePasswordAndId(SecurityGuard securityGuard);

    /**
     * @Method selectAll
     * @Author 陈宝梁
     * @Description  查询所有员工
     * @Date 2021/11/22 21:28
     **/
    List<SecurityGuard> selectAll();

    /**
     * @Method selectByOnDay
     * @Author 陈宝梁
     * @Description 查询当班人员
     * @Date 2021/11/25 11:26
     **/
    List<SecurityGuard> selectByOnDay();

    /**
     * 根据权限查询
     * @param role
     * @return
     */
    List<SecurityGuard> selectByRole(@Param("role") String role);

    /**
     * @Method updateSelective
     * @Author 陈宝梁
     * @Description 修改信息 当班/不当版  核酸检测员/门岗检测员
     * @Date 2021/11/22 21:36
     **/
    int updateSelective(List<SecurityGuard> securityGuard);

    /**
     * @Method updateOnDayById
     * @Author 陈宝梁
     * @Description 根据id 修改值班状态
     * @Date 2021/11/22 21:40
     **/
    int updateOnDayById(List<SecurityGuard> securityGuard);

    /**
     * 清楚所有当班人员 除防疫管理员外
     * @return
     */
    int updateOnDay(@Param("onDay")Boolean onDay);

}
