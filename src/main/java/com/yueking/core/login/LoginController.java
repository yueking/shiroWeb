package com.yueking.core.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("intoLogin")
    public String intoLogin(){
        return "login";
    }

    @RequestMapping("subLogin")
    public String subLogin(){
        System.out.println("====subLogin");
        /*获得主体*/
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken taken = new UsernamePasswordToken(user.getUsername(),user.getPassword());
//
//        try {
//            subject.login(taken);
//        } catch (AuthenticationException e) {
//            //e.printStackTrace();
//            return e.getMessage();
//        }

        return "home";

    }
}
