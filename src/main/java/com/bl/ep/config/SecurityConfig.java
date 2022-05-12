package com.bl.ep.config;

import com.bl.ep.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @ClassName SecurityConfig
 * @Description MVC Security管理配置的自定义WebSecurityConfigurerAdapter类
 * @Author 陈宝梁
 * @Date 2021/11/17 20:53
 * @Version 1.0
 **/
@Configuration
//@EnableWebSecurity  //开启 MVC security 安全支持
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/img/**",
                "/favicon.ico",
                "/css/**",
                "/js/**");
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new EPPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用 UserDetailsService进行身份认证
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //自定义用户登录控制
        http.formLogin()
                .loginPage("/login/userLogin")
                .loginProcessingUrl("/login/login")
                .successForwardUrl("/login/index")
                .failureUrl("/login/userLogin?error");//使用form表单登录
        //自定义用户退出控制
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login/userLogin");
//                .invalidateHttpSession(true)
//                .clearAuthentication(true);
        //定制Remember-me记住我功能
        http.rememberMe()
                .rememberMeParameter("rememberme")
                //一周  默认两周
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                .tokenRepository(persistentTokenRepository)
                //自定义登录逻辑
                .userDetailsService(userDetailsService);
        //关闭spring security默认开启的CSRF功能
//        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(loadExcludePath()).permitAll()//对次放行
                .anyRequest().authenticated();//其余请求都需要验证
    }

    /**
     * 后端接口放行
     */
    private String[] loadExcludePath() {
        return new String[]{
                "/",
                "/login/userLogin"
        };
    }

    /**
     * 持久化Token存储
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jr = new JdbcTokenRepositoryImpl();
        jr.setDataSource(dataSource);
        //初启动使用
//        jr.setCreateTableOnStartup(true);
        return jr;
    }


}
