package com.ye.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021年07月04日 17:12
 */
@Configuration
public class ShiroConfig {

    // ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 添加 shiro 内置过滤器
        /*
         * anon : 无需认证即可访问 ------ 无需登陆
         * authc : 必须认证才能访问 ------ 需要登陆
         * user : 必须拥有记住我功能才能用 ------
         * perms ：拥有对某个资源的权限才能访问 ------ 需要登陆并有权限
         * roles : 拥有某个角色权限才能访问
         */
        HashMap<String, String> map = new HashMap<>();
        // 授权，未授权会跳转未授权页面
        map.put("/user/select", "anon");
        map.put("/user/delete", "perms[user:delete]");
        map.put("/user/add", "authc");
        map.put("/user/update", "perms[user:update]");
        map.put("/user/vip", "perms[user:update,user:delete]");
/*      map.put("*","authc"); // 注意先后顺序*/
        map.put("/login","anon");
        map.put("/logout","anon");
        map.put("/user/vip2","roles[vip2]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        // 设置登陆请求
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/Unauthorized");

        return shiroFilterFactoryBean;
    }

    // DefaultwebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDafaultwebSecurityManager(@Autowired UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 关联 userRealm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    // 创建 reaLm 对象
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    // 整合 shiroDialect
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
