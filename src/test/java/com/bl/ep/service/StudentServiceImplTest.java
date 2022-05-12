package com.bl.ep.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName StudentServiceImplTest
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/17 15:14
 * @Version 1.0
 **/
@SpringBootTest
public class StudentServiceImplTest {
    @Autowired
    StudentService studentService;

    @Test
    void findById(){
        studentService.findByPrimaryKey(1L);
    }
}
