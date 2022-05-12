package com.bl.ep.service;

import com.bl.ep.bean.Student;

/**
 * @ClassName AdminStudentService
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/15 21:34
 * @Version 1.0
 **/
public interface AdminStudentService {
    /**
     * 插入数据
     * @param student
     * @return
     */
    public int addStudent(Student student);
}
