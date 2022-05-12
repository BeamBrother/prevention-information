package com.bl.ep.service;

import com.bl.ep.bean.SecurityGuard;

/**
 * @ClassName AdminSecurityGuardService
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/15 21:35
 * @Version 1.0
 **/
public interface AdminSecurityGuardService {
    /**
     * 添加新工作人员数据
     * @param securityGuard
     * @return
     */
    public int addSecurityGuard(SecurityGuard securityGuard);
}
