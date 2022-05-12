package com.bl.ep.service;

import com.bl.ep.bean.HealthCode;

import java.util.List;

/**
 * @ClassName HealthCodeService
 * @Description 健康码业务逻辑
 * @Author 陈宝梁
 * @Date 2021/11/16 23:15
 * @Version 1.0
 **/
public interface HealthCodeService {
    /**
     * @Method findByPrimaryKey
     * @Author 陈宝梁
     * @Description 根据 id 查找健康码
     * @Date 2021/11/16 23:19
     **/
    public HealthCode findByPrimaryKey(String id);

    /**
     * @Method findByStudent
     * @Author 陈宝梁
     * @Description 根据学生 查找对应的健康码
     * @Date 2021/11/16 23:19
     **/
    public HealthCode findByStudent(Long id);

    /**
     * @Method findAll
     * @Author 陈宝梁
     * @Description  查询所有
     * @Date 2021/11/22 12:55
     **/
    public List<HealthCode> findAll();

    /**
     * @Method  findByStudentAndNo
     * @Author 陈宝梁
     * @Description  根据学号和姓名查询学生健康码
     * @Date 2021/11/25 10:53
     **/
    public HealthCode findByStudentAndNo(String sno);

    /**
     * 添加健康码信息
     */
    public int addHealthCode(HealthCode healthCode);

    /**
     * @Method updateByHealthCode
     * @Author 陈宝梁
     * @Description 修改健康码状态
     * @Date 2021/11/16 23:20
     **/
    public int updateByHealthCode(HealthCode healthCode);

}
