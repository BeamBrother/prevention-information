package com.bl.ep.mapper;

import com.bl.ep.bean.Role;
import com.bl.ep.bean.SecurityGuard;
import com.bl.ep.utils.DateUtil;
import com.bl.ep.utils.security.MD5Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName SecurityGuardMapperTest
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/18 11:20
 * @Version 1.0
 **/
@SpringBootTest
public class SecurityGuardMapperTest {
    @Resource
    private SecurityGuardMapper securityGuardMapper;
    @Resource
    private AdminSecurityGuardMapper adminSecurityGuardMapper;

    @Test
    void selectByNo() {

        SecurityGuard securityGuard = securityGuardMapper.selectByAccount("123456");
        Assertions.assertNotNull(securityGuard);
        System.out.println(securityGuard);
    }

    @Test
    void insertTest() throws ParseException {
        SecurityGuard securityGuard = new SecurityGuard();
        securityGuard.setNo("123457");
        securityGuard.setPassword(MD5Utils.md5("123456"));
        securityGuard.setEmail("123@sise.cn");

        securityGuard.setBirthday(DateUtil.stringToDate("1999-04-21","yyyy-MM-dd"));
        Role role = new Role();
        role.setId(3);
        role.setRole("ROLE_GUARD");

        securityGuard.setRole(role);
        securityGuard.setPhone("12345678908");
        securityGuard.setUsername("李四");
        securityGuard.setOnDay(true);
        adminSecurityGuardMapper.insertSelective(securityGuard);
    }

    @Test
    void insertTest2() throws ParseException {
        SecurityGuard securityGuard = new SecurityGuard();
        securityGuard.setNo("0123");
        securityGuard.setPassword(MD5Utils.md5("123456"));
        securityGuard.setEmail("123@sise.cn");

        securityGuard.setBirthday(DateUtil.stringToDate("1986-04-21","yyyy-MM-dd"));
        Role role = new Role();
        role.setId(5);
        role.setRole("ROLE_MG");

        securityGuard.setRole(role);
        securityGuard.setPhone("12345678908");
        securityGuard.setUsername("王五");
        securityGuard.setOnDay(false);
        adminSecurityGuardMapper.insertSelective(securityGuard);
    }
}
