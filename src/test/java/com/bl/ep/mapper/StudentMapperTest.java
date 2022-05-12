package com.bl.ep.mapper;

import com.bl.ep.bean.Role;
import com.bl.ep.bean.Student;
import com.bl.ep.utils.DateUtil;
import com.bl.ep.utils.security.MD5Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName StudentMapperTest
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/15 22:37
 * @Version 1.0
 **/
@SpringBootTest
public class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;
    @Resource
    private AdminStudentMapper adminStudentMapper;

    @Test
    void inserTest() throws ParseException {
        Student student = new Student();
        student.setNo("1952134256");
        student.setUsername("张无忌");
        student.setEmail("123456@sise.cn");
        student.setPassword(MD5Utils.md5("123456"));
        student.setDepartment("软件工程系");
        student.setMajor("软件工程");
        student.setBirthday(DateUtil.stringToDate("2000-05-02","yyyy-MM-dd"));
        Role role = new Role();
        role.setId(1);
        role.setRole("ROLE_STUDENT");
        student.setRole(role);
        adminStudentMapper.insertSelective(student);
    }

    @Test
    void selectTest() {
        studentMapper.selectByPrimaryKey(1L);
    }

    @Test
    void selectByNameAndNo(){


        studentMapper.selectByNameAndNo("1952134256","张三");
    }
    @Test
    void selectByNo(){
        studentMapper.selectByAccount("1952134256");
    }

    @Test
    void sytest(){
        String admin123456789 = MD5Utils.md5("admin123456789");
        System.out.println(admin123456789);
    }
}
