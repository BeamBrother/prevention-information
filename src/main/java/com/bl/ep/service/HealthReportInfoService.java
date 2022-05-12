package com.bl.ep.service;

import com.bl.ep.bean.HealthReportInfo;

import java.util.Date;
import java.util.List;

/**
 * @ClassName HealthReportInfoService
 * @Description 签到 业务逻辑
 * @Author 陈宝梁
 * @Date 2021/11/16 23:20
 * @Version 1.0
 **/
public interface HealthReportInfoService {
    /**
     * @Method addSelective
     * @Author 陈宝梁
     * @Description 添加 签到信息
     * @Date 2021/11/16 23:22
     **/
    public int addSelective(HealthReportInfo healthReportInfo);

    /**
     * @Method findAll
     * @Author 陈宝梁
     * @Description 查找某天的签到信息
     * @Date 2021/11/16 23:22
     **/
    public List<HealthReportInfo> findAll(Date reportTime);
}
