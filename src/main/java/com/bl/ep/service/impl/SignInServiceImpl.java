package com.bl.ep.service.impl;

import com.bl.ep.bean.SignIn;
import com.bl.ep.mapper.SignInMapper;
import com.bl.ep.service.SignInService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SignInServiceImpl
 * @Description 签到信息业务逻辑实现类
 * @Author 陈宝梁
 * @Date 2021/12/21 10:53
 * @Version 1.0
 **/
@Service("signInService")
public class SignInServiceImpl implements SignInService {
    @Resource
    private SignInMapper signInMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addSignIn(SignIn signIn) {
        if (signIn == null){
            return 0;
        }
        return signInMapper.insertSignIn(signIn);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int modifySignIn(SignIn signIn) {
        if (signIn == null){
            return 0;
        }
        return signInMapper.updateSignIn(signIn);
    }

    @Override
    public List<SignIn> findSignInByStudentNo(String sno) {
        return signInMapper.selectByStudentNno(sno);
    }

    @Override
    public List<SignIn> findAll() {
        return signInMapper.selectAll();
    }
}
