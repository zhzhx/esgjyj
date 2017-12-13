package com.eastsoft.esgjyj.controller;

import com.eastsoft.esgjyj.util.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**

 */
@RestController
public class LoginController {
    @GetMapping("/")
    void login(HttpServletResponse response){
        try {
            response.sendRedirect("/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/ajaxLogin")
    R ajaxLogin(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return R.ok();
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }
}
