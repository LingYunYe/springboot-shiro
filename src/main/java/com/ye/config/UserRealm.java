package com.ye.config;

import com.ye.Mapper.UserMapper;
import com.ye.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021年07月04日 17:15
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("执行了授权方法 =》doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        String[] arr = user.getPerms().split(",");
/*        for (String s : arr) {
            info.addStringPermission(s);
        }*/
        info.addStringPermissions(Arrays.asList(arr));
        info.addRoles(Arrays.asList(user.getRoles().split(",")));
        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("执行了认证方法 =》doGetAuthenticationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userMapper.selectUserByName(userToken.getUsername());
        if (user==null) {
            return null; // 返回空抛出异常用户名错误 UnknownAccountException
        }
        // 可以加密 MD5  MD5加盐加密
        // 密码校验 shiro 做 存入 user, User user = (User) subject.getPrincipal() 可以获取存入得值
        return new SimpleAuthenticationInfo(user, user.getPwd(),null, "");
    }
}
