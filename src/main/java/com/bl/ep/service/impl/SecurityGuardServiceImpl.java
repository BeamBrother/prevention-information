package com.bl.ep.service.impl;

import com.bl.ep.bean.SecurityGuard;
import com.bl.ep.mapper.SecurityGuardMapper;
import com.bl.ep.service.SecurityGuardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SecurityGuardServiceImpl
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/17 15:07
 * @Version 1.0
 **/
@Service("securityGuardService")
public class SecurityGuardServiceImpl implements SecurityGuardService {
    @Resource
    private SecurityGuardMapper securityGuardMapper;

    @Override
    public SecurityGuard findByAccount(String no) {
        return securityGuardMapper.selectByAccount(no);
    }

    @Override
    public SecurityGuard findByPrimaryKey(Long id) {
        return securityGuardMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int modifyPassword(SecurityGuard securityGuard) {
        return securityGuardMapper.updatePasswordAndId(securityGuard);
    }

    @Override
    public List<SecurityGuard> findAll() {
        return securityGuardMapper.selectAll();
    }

    @Override
    public List<SecurityGuard> findByOnDay() {
        return securityGuardMapper.selectByOnDay();
    }

    @Override
    public List<SecurityGuard> findByRole(String role) {
        return securityGuardMapper.selectByRole(role);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int modifySelective(List<SecurityGuard> securityGuards) {
        return securityGuardMapper.updateSelective(securityGuards);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int modifyOnDayById(List<SecurityGuard> securityGuards) {
        return securityGuardMapper.updateOnDayById(securityGuards);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int modifyOnDay(Boolean onDay) {
        return securityGuardMapper.updateOnDay(onDay);
    }
}
