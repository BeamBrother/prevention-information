package com.bl.ep.service;

import com.bl.ep.bean.NucleicAcidDetect;

import java.util.List;

/**
 * @ClassName NucleicAcidDetectService
 * @Description 核酸检测信息 业务逻辑
 * @Author 陈宝梁
 * @Date 2021/11/16 23:23
 * @Version 1.0
 **/
public interface NucleicAcidDetectService {
    /**
     * @Method addSelective
     * @Author 陈宝梁
     * @Description 检测信息录入
     * @Date 2021/11/16 23:24
     **/
    public int addSelective(NucleicAcidDetect nucleicAcidDetect);

    /**
     * @Method selectByUserId
     * @Author 陈宝梁
     * @Description 根据用户 no 查询其所有的检测信息
     * @Date 2021/11/16 23:24
     **/
    public List<NucleicAcidDetect> findByUserNo(String no);

    /**
     * @Method findByPrimaryKey
     * @Author 陈宝梁
     * @Description  根据 检测信息id 查询检测信息
     * @Date 2021/11/22 20:17
     * @param id 检测信息表的唯一标识
     **/
    public NucleicAcidDetect findByPrimaryKey(String id);

    /**
     * @Method findAll
     * @Author 陈宝梁
     * @Description  查询所哟检测结果
     * @Date 2021/11/22 18:18
     **/
    public List<NucleicAcidDetect> findAll();
}
