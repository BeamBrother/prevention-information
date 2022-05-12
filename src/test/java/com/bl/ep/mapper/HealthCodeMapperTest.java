package com.bl.ep.mapper;

import com.bl.ep.bean.HealthCode;
import com.bl.ep.bean.Student;
import com.bl.ep.service.HealthCodeService;
import com.bl.ep.utils.IdUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @ClassName HealthCodeMapperTest
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/22 10:21
 * @Version 1.0
 **/
@SpringBootTest
public class HealthCodeMapperTest {
    @Resource
    private HealthCodeMapper healthCodeMapper;
    @Resource
    private StudentMapper studentMapper;

    @Test
    void insertTest(){
        Student student = studentMapper.selectByAccount("1952134256");
        HealthCode healthCode = new HealthCode(IdUtils.getIncreaseIdByCurrentTimeMillis(),Integer.valueOf("2"),student);
        healthCodeMapper.insertBySelective(healthCode);
    }
}
