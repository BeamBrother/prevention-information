package com.bl.ep.service.impl;

import com.bl.ep.bean.Role;
import com.bl.ep.mapper.RoleMapper;
import com.bl.ep.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/22 23:36
 * @Version 1.0
 **/
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Override
    public Role findById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Role findByRoleStr(String role) {
        return roleMapper.selectByRoleStr(role);
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.selectAll();
    }
}
