package com.bl.ep.service;

import com.bl.ep.bean.SecurityGuard;

import java.util.List;

/**
 * @ClassName SecurityGuardService
 * @Description 门岗检测员的业务逻辑
 * @Author 陈宝梁
 * @Date 2021/11/16 23:00
 * @Version 1.0
 **/
public interface SecurityGuardService {
    /**
     * @Method findByAccount
     * @Author 陈宝梁
     * @Description 门岗检测员登录业务逻辑
     * @Date 2021/11/16 23:03
     **/
    public SecurityGuard findByAccount(String no);

    /**
     * @Method findByPrimaryKey
     * @Author 陈宝梁
     * @Description 根据id查找门岗检测员
     * @Date 2021/11/16 23:04
     **/
    public SecurityGuard findByPrimaryKey(Long id);

    /**
     * 修改密码
     * @param securityGuard
     */
    public int modifyPassword(SecurityGuard securityGuard);

    /**
     * 查询所有
     * @return
     */
    public List<SecurityGuard> findAll();

    /**
     * 查询当班人员
     */
    public List<SecurityGuard> findByOnDay();

    /**
     * 查询对应权限人员
     */
    public List<SecurityGuard> findByRole(String role);

    /**
     * 修改信息 当班/不当版  核酸检测员/门岗检测员
     * @param securityGuards
     * @return
     */
    public int modifySelective(List<SecurityGuard> securityGuards);

    /**
     * 根据id 修改值班状态
     * @param securityGuards
     * @return
     */
    public int modifyOnDayById(List<SecurityGuard> securityGuards);

    /**
     * 清楚所有当班状态
     * @return
     */
    public int modifyOnDay(Boolean onDay);

}
