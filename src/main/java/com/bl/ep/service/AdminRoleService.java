package com.bl.ep.service;

import com.bl.ep.bean.Role;

/**
 * @ClassName AdminRoleService
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/15 21:36
 * @Version 1.0
 **/
public interface AdminRoleService {
    /**
     * 添加新的角色
     * @param role
     * @return
     */
    public int addRole(Role role);
}
