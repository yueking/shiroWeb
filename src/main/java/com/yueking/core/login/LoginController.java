package com.yueking.core.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
