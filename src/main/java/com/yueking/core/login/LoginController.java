package com.yueking.core.login;

import com.yueking.core.shiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String subLogin(HttpServletRequest request,User user){
        System.out.println("====subLogin"+user);
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
