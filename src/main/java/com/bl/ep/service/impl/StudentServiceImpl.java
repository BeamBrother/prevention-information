package com.bl.ep.service.impl;

import com.bl.ep.bean.Student;
import com.bl.ep.mapper.StudentMapper;
import com.bl.ep.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/17 15:06
 * @Version 1.0
 **/
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public Student findByPrimaryKey(Long id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Student findByNameAndNo(String no, String username) {
        return studentMapper.selectByNameAndNo(no, username);
    }

    @Override
    public List<Student> findAll() {
        return studentMapper.selectAll();
    }

    @Override
    public Student findByAccount(String no) {
        return studentMapper.selectByAccount(no);
    }

    @Transactional
    @Override
    public int modifyPassword(Student student) {
        return studentMapper.updatePasswordAndId(student);
    }

}
