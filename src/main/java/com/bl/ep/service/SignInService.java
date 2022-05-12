package com.bl.ep.service;

import com.bl.ep.bean.SignIn;

import java.util.List;

/**
 * @ClassName SignInService
 * @Description 签到业务逻辑
 * @Author 陈宝梁
 * @Date 2021/12/21 10:50
 * @Version 1.0
 **/
public interface SignInService {
    /**
     * 插入新纪录
     * @param signIn
     * @return
     */
    public int addSignIn(SignIn signIn);

    /**
     * 修改记录
     * @param signIn
     * @return
     */
    public int modifySignIn(SignIn signIn);

    /**
     * 根据学号查询记录
     * @param sno  学号
     * @return
     */
    public List<SignIn> findSignInByStudentNo(String sno);

    /**
     * 查询所有
     * @return
     */
    public List<SignIn> findAll();
}
