package com.bl.ep.service.impl;

import com.bl.ep.bean.HealthCode;
import com.bl.ep.bean.HealthReportInfo;
import com.bl.ep.mapper.HealthReportInfoMapper;
import com.bl.ep.service.HealthReportInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName HealthReportInfoServiceImpl
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/17 15:33
 * @Version 1.0
 **/
@Service("healthReportInfoService")
public class HealthReportInfoServiceImpl implements HealthReportInfoService {
    @Resource
    private HealthReportInfoMapper healthReportInfoMapper;

    @Transactional
    @Override
    public int addSelective(HealthReportInfo healthReportInfo) {
        return healthReportInfoMapper.insertSelective(healthReportInfo);
    }

    @Override
    public List<HealthReportInfo> findAll(Date reportTime) {
        return healthReportInfoMapper.selectAll(reportTime);
    }
}
