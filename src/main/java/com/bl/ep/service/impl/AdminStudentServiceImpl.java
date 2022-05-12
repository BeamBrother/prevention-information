package com.bl.ep.service.impl;

import com.bl.ep.anno.InsertManagerPower;
import com.bl.ep.bean.Student;
import com.bl.ep.mapper.AdminStudentMapper;
import com.bl.ep.service.AdminStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName AdminStudentServiceImpl
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/15 21:37
 * @Version 1.0
 **/
@Service("adminStudentService")
public class AdminStudentServiceImpl implements AdminStudentService {
    @Resource
    private AdminStudentMapper adminStudentMapper;

    @InsertManagerPower
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addStudent(Student student) {
        if (student == null) {
            return 0;
        }
        return adminStudentMapper.insertSelective(student);
    }
}
