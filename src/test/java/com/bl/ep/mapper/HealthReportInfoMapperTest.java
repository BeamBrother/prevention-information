package com.bl.ep.mapper;

import com.bl.ep.bean.HealthReportInfo;
import com.bl.ep.bean.Student;
import com.bl.ep.utils.Constant;
import com.bl.ep.utils.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName HealthReportInfoMapperTest
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/27 16:18
 * @Version 1.0
 **/
@SpringBootTest
public class HealthReportInfoMapperTest {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private HealthReportInfoMapper healthReportInfoMapper;
    @Test
    void insert() throws ParseException {
        Student student = studentMapper.selectByPrimaryKey(Long.valueOf("1"));
        HealthReportInfo healthReportInfo = new HealthReportInfo(true, student, DateUtil.dateFormat(new Date(), Constant.YYYY_DD_MM));
        int i = healthReportInfoMapper.insertSelective(healthReportInfo);
        Assertions.assertNotNull(i);
    }

    @Test
    void select() throws ParseException {
        List<HealthReportInfo> healthReportInfos = healthReportInfoMapper.selectAll(DateUtil.stringToDate("2021-11-27", Constant.YYYY_DD_MM));
        Assertions.assertNotNull(healthReportInfos);
        System.out.println(healthReportInfos);
    }
}
