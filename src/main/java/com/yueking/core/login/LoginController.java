package com.yueking.core.login;

import com.yueking.core.shiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            try{
                subject.logout();
            }catch(Exception ex){
            }
        }
        return "success";
    }

    @RequestMapping("subLogin")
    public ModelAndView subLogin(User user, Model model) throws Exception {
        System.out.println("====subLogin"+user);
        String message=null;


        /*获得主体*/
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        token.setRememberMe(true);

        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                System.out.println("---isAuthenticated:"+subject.isAuthenticated());
                if (subject.hasRole("admin")) {
                    System.out.println("--admin Account");
                } else {
                    System.out.println("--user Account");
                }

                if (subject.isPermitted("sys")) {
                    System.out.println("-- sys permission");
                }

                if (subject.isPermitted("pub")) {
                    System.out.println("-- pub permission");
                }
            }
        } catch (UnknownAccountException e) {
            System.out.println("=====UnknownAccountException");
            message = "UnknownAccountException";
//            throw new UnknownAccountException();
        }catch (IncorrectCredentialsException ex) {
//            return "用户不存在或者密码错误！";
            message = "password error";
        } catch (AuthenticationException ex) {
            message = "AuthenticationException";
//            return ex.getMessage(); // 自定义报错信息
        } catch (Exception ex) {
            ex.printStackTrace();
//            return "内部错误，请重试！";
            message = "error";
        }
        if (message == null) {
            ModelAndView view = new ModelAndView();
            view.setViewName("home");
            return view;
        } else {
            ModelAndView view = new ModelAndView();
            view.setViewName("login");
            view.addObject("message", message);
            return view;
        }


    }
}
