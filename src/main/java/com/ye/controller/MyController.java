package com.ye.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021年07月04日 16:54
 */
@Controller
public class MyController {
    @GetMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello shiro");
        return "index";
    }

    @GetMapping("/user/add")
    public String add() {
        return "user/add";
    }

    @GetMapping("/user/update")
    public String update() {
        return "user/update";
    }

    @GetMapping("/user/vip")
    public String vip() {
        return "user/vip";
    }

    @GetMapping("/user/vip2")
    public String vip2() {
        return "user/vip2";
    }

    @GetMapping("/user/delete")
    public String delete(Model model) {
        model.addAttribute("msg", "你拥有删除权限");
        return "user/delete";
    }

    @GetMapping("/user/select")
    public String select() {
        return "user/select";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/toLogin")
    public String toLogin(String userName, String password, Model model) {
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登陆数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        token.setRememberMe(true); // 记住我
        System.out.println(token.isRememberMe());
        try {
            subject.login(token);
            // 测试在 Session 存放消息，此 session 非 request session，可以全局 subject.getSession().getAttribute("userName")获取
            Session session = subject.getSession();
            session.setAttribute("loginUser",userName);
            return "index";
        } catch (UnknownAccountException e) { // 用户名错误
            model.addAttribute("msg", "用户名错误");
            return "login";
        } catch (IncorrectCredentialsException inc) { // 密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    @RequestMapping("/Unauthorized")
    @ResponseBody
    public String Unauthorized() {
        return "未经授权请联系授权";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}

