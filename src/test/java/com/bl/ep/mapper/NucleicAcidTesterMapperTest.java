package com.bl.ep.mapper;

import com.bl.ep.bean.Role;
import com.bl.ep.bean.SecurityGuard;
import com.bl.ep.service.SecurityGuardService;
import com.bl.ep.utils.DateUtil;
import com.bl.ep.utils.security.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;

/**
 * @ClassName NucleicAcidTesterMapperTest
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/21 22:46
 * @Version 1.0
 **/
@SpringBootTest
public class NucleicAcidTesterMapperTest {
    @Resource
    private SecurityGuardMapper securityGuardMapper;
    @Resource
    private AdminSecurityGuardMapper adminSecurityGuardMapper;
    @Test
    void insert() throws ParseException {
        SecurityGuard nucleicAcidTester = new SecurityGuard();
        nucleicAcidTester.setNo("012345");
        nucleicAcidTester.setPassword(MD5Utils.md5("123456"));
        nucleicAcidTester.setEmail("123@sise.cn");

        nucleicAcidTester.setBirthday(DateUtil.stringToDate("1992-04-21","yyyy-MM-dd"));
        Role role = new Role();
        role.setId(4);
        role.setRole("ROLE_TESTER");

        nucleicAcidTester.setRole(role);
        nucleicAcidTester.setPhone("12345678908");
        nucleicAcidTester.setUsername("海波");
        nucleicAcidTester.setOnDay(true);
        adminSecurityGuardMapper.insertSelective(nucleicAcidTester);
    }
}
