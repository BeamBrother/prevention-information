package com.bl.ep.mapper;

import com.bl.ep.bean.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @ClassName AdminStudentMapperTest
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/23 21:24
 * @Version 1.0
 **/
@SpringBootTest
public class AdminStudentMapperTest {
    @Resource
    private AdminStudentMapper adminStudentMapper;

    @Test
    void select(){
        Student student = adminStudentMapper.selectByPrimaryKey(1L);
        Assertions.assertNotNull(student);
        System.out.println(student);
    }
}
