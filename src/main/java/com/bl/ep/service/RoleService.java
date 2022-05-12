package com.bl.ep.service;

import com.bl.ep.bean.Role;

import java.util.List;

/**
 * @ClassName RoleService
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/22 23:34
 * @Version 1.0
 **/
public interface RoleService {
    public Role findById(Integer id);

    public Role  findByRoleStr(String role);

    public List<Role> findAll();
}
