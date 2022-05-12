package com.bl.ep.service;

import com.bl.ep.bean.Student;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @ClassName StudentService
 * @Description 学生的业务逻辑
 * @Author 陈宝梁
 * @Date 2021/11/16 17:28
 * @Version 1.0
 **/
public interface StudentService {

    /**
     * @Method findByPrimaryKey
     * @Author 陈宝梁
     * @Description 根据 id 查询学生
     * @Date 2021/11/16 17:30
     **/
    public Student findByPrimaryKey(Long id);

    /**
     * @Method findByNameAndNo
     * @Author 陈宝梁
     * @Description 根据 学号和姓名 查询学生
     * @Date 2021/11/16 17:33
     **/
    public Student findByNameAndNo(String no, String username);

    /**
     * @Method findAll
     * @Author 陈宝梁
     * @Description 查询所有学生
     * @Date 2021/11/16 17:33
     **/
    public List<Student> findAll();

    /**
     * @Method findByAccount
     * @Author 陈宝梁
     * @Description 根据学号查询
     * @Date 2021/11/16 17:34
     **/
    public Student findByAccount(String no);

    /**
     * 修改密码
     * @param student
     */
    public int modifyPassword(Student student);

}
