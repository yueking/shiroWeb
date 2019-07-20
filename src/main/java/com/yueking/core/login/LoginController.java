package com.yueking.core.login;

import com.yueking.core.shiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @RequestMapping("intoHome")
    public String index(){
        return "home";
    }

    @RequestMapping("unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @RequestMapping("intoLogin")
    public String intoLogin(){
        return "login";
    }

    @RequestMapping("subLogin")
    public String subLogin(HttpServletRequest request, User user) throws Exception {
        System.out.println("====subLogin"+user);

        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        if (exceptionClassName != null) {
            if (exceptionClassName.equals(UnknownAccountException.class.getName())) {
                //todo throw Exception("用户不存在");
                throw new Exception("用户不存在");
            } else if (exceptionClassName.equals(IncorrectCredentialsException.class.getName())) {
                //todo throw Exception("用户名/密码错误");
                throw new Exception("用户名/密码错误");
            } else {
                throw new Exception();
            }
        }

        /*获得主体*/
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken taken = new UsernamePasswordToken(user.getUsername(),user.getPassword());

        try {
            subject.login(taken);
            if (subject.isAuthenticated()) {
                return "home";
            }

        } catch (AuthenticationException e) {
            //e.printStackTrace();
            return e.getMessage();
        }
        return "unauthorized";

    }
}
