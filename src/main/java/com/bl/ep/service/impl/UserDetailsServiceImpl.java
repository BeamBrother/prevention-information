package com.bl.ep.service.impl;

import com.bl.ep.bean.EPManager;
import com.bl.ep.bean.SecurityGuard;
import com.bl.ep.bean.Student;
import com.bl.ep.mapper.EPManagerMapper;
import com.bl.ep.mapper.SecurityGuardMapper;
import com.bl.ep.mapper.StudentMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/17 19:18
 * @Version 1.0
 **/
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private SecurityGuardMapper securityGuardMapper;
    @Resource
    private EPManagerMapper epManagerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentMapper.selectByAccount(username);
        SecurityGuard securityGuard = securityGuardMapper.selectByAccount(username);
        EPManager epManager = epManagerMapper.selectByAccount(username);

        //返回封装的UserDetails用户详情类
        if (student != null) {
            return new User(student.getNo(), student.getPassword(), grantedAuthorityList(student.getRole().getRole()));
        } else if (securityGuard != null) {
            return new User(securityGuard.getNo(), securityGuard.getPassword(), grantedAuthorityList(securityGuard.getRole().getRole()));
        }else if (epManager != null) {
            return new User(epManager.getAccount(), epManager.getPassword(), grantedAuthorityList(epManager.getRole().getRole()));
        }else {
            // 如果查询的用户不存在必须（学号/工号不存在），必须抛出此异常
            throw new UsernameNotFoundException("当前用户不存在！");
        }

    }

    private List<GrantedAuthority> grantedAuthorityList(String authorityString) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString);
    }
}
