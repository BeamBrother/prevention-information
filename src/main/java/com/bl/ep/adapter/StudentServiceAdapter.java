package com.bl.ep.adapter;

import com.bl.ep.bean.Student;
import com.bl.ep.mapper.StudentMapper;
import com.bl.ep.service.StudentService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName StudentServiceAdapter
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/16 7:47
 * @Version 1.0
 **/
public class StudentServiceAdapter implements StudentService {

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
