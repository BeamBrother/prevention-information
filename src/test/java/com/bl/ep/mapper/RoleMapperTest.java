package com.bl.ep.mapper;

import com.bl.ep.bean.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @ClassName RoleMapperTest
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/12/1 17:39
 * @Version 1.0
 **/
@SpringBootTest
public class RoleMapperTest {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private AdminRoleMapper adminRoleMapper;

    @Test
    void update(){
        Role role = roleMapper.selectByPrimaryKey(6);
        role.setDescribe("超级管理员");
        int i = adminRoleMapper.updateSelective(role);
        Assertions.assertNotNull(i);
    }
}
