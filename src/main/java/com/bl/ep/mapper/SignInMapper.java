package com.bl.ep.mapper;

import com.bl.ep.bean.SignIn;
import com.bl.ep.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SignInMapper
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/12/20 23:14
 * @Version 1.0
 **/
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface SignInMapper {
    /**
     * 添加新纪录
     * @param signIn
     * @return
     */
    int insertSignIn(SignIn signIn);

    /**
     * 修改签到信息
     * @param signIn
     * @return
     */
    int updateSignIn(SignIn signIn);

    /**
     * 根据学号学生id铲鲟信息
     * @param sno
     * @return
     */
    List<SignIn> selectByStudentNno(@Param("sno") String sno);

    /**
     * 查询所有
     * @return
     */
    List<SignIn> selectAll();
}
