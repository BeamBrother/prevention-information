package com.bl.ep.service.impl;

import com.bl.ep.bean.SecurityGuard;
import com.bl.ep.mapper.AdminSecurityGuardMapper;
import com.bl.ep.service.AdminSecurityGuardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName AdminSecurityGuardServiceImpl
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/15 21:39
 * @Version 1.0
 **/
@Service("adminSecurityGuardService")
public class AdminSecurityGuardServiceImpl implements AdminSecurityGuardService {
    @Resource
    private AdminSecurityGuardMapper adminSecurityGuardMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addSecurityGuard(SecurityGuard securityGuard) {
        if (securityGuard == null) {
            return 0;
        }
        return adminSecurityGuardMapper.insertSelective(securityGuard);
    }
}
