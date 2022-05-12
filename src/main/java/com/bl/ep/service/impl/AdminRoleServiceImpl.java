package com.bl.ep.service.impl;

import com.bl.ep.bean.Role;
import com.bl.ep.mapper.AdminRoleMapper;
import com.bl.ep.service.AdminRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName AdminRoleServiceImpl
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/15 21:40
 * @Version 1.0
 **/
@Service("adminRoleService")
public class AdminRoleServiceImpl implements AdminRoleService {
    @Resource
    private AdminRoleMapper adminRoleMapper;
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addRole(Role role) {
        if (role == null) {
            return 0;
        }
        return adminRoleMapper.insertSelective(role);
    }
}
