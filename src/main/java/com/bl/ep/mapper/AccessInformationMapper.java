package com.bl.ep.mapper;

import com.bl.ep.bean.AccessInformation;
import com.bl.ep.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @ClassName AccessInformationMapper
 * @Description 进出信息 dao
 * @Author 陈宝梁
 * @Date 2021/11/15 18:19
 * @Version 1.0
 **/
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface AccessInformationMapper {
    /**
     * @Method insertSelective
     * @Author 陈宝梁
     * @Description 添加出入信息
     * @Date 2021/11/15
     **/
    int insertSelective(AccessInformation accessInformation);

    /**
     * @Method selectByPrimaryKey
     * @Author 陈宝梁
     * @Description 根据id查询 出入信息
     * @Date 2021/11/15
     **/
    AccessInformation selectByPrimaryKey(@Param("id") String id);

    /**
     * @Method selectByTimeAndId
     * @Author 陈宝梁
     * @Description 根据日期查询出入信息
     * @Date 2021/11/16 17:25
     **/
    List<AccessInformation> selectByTimeAndId(@Param("start")Date start,@Param("end")Date end);

    /**
     * @Method selectAll
     * @Author 陈宝梁
     * @Description 查询所有出入信息
     * @Date 2021/11/15
     **/
    List<AccessInformation> selectAll();

    /**
     * 根据 学生id 查找出入信息
     * @param id 学生表唯一标识
     * @return
     */
    List<AccessInformation> selectAllByStudent(@Param("stu_id") Long id);

    /**
     * 查询总数
     */
    Long selectCount();

    /**
     * 根据学号和姓名查找学生
     * @param no  学号
     * @param name  姓名
     */
    List<AccessInformation> selectBySnoAndSname(@Param("stu_no")String no, @Param("stu_name")String name);

}
