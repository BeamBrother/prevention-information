package com.bl.ep.service;

import com.bl.ep.bean.AccessInformation;

import java.util.Date;
import java.util.List;

/**
 * @ClassName AccessInformationService
 * @Description 进出信息 业务逻辑
 * @Author 陈宝梁
 * @Date 2021/11/16 23:25
 * @Version 1.0
 **/
public interface AccessInformationService {
    /**
     * @Method addSelective
     * @Author 陈宝梁
     * @Description 添加出入信息
     * @Date 2021/11/16 23:29
     **/
    public int addSelective(AccessInformation accessInformation);

    /**
     * @Method findByPrimaryKey
     * @Author 陈宝梁
     * @Description 根据id查询 出入信息
     * @Date 2021/11/16 23:29
     **/
    public AccessInformation findByPrimaryKey(String id);

    /**
     * @Method findByTimeAndId
     * @Author 陈宝梁
     * @Description 根据日期查询出入信息
     * @Date 2021/11/16 23:29
     * @param start 开始时间
     * @param end   结束时间
     **/
    public List<AccessInformation> findByTimeAndId(Date start, Date end);

    /**
     * @Method findAll
     * @Author 陈宝梁
     * @Description 查询所有出入信息
     * @Date 2021/11/16 23:29
     **/
    public List<AccessInformation> findAll();

    /**
     * 查询该学生的出入信息
     *
     * @param id 唯一标识
     */
    public List<AccessInformation> findAllByStudent(Long id);

    /**
     * 根据学号 和 姓名 查找学生
     * @param no  学号
     * @param name  姓名
     */
    public List<AccessInformation> findAccessInfoByStudent(String no,String name);
}
