package com.yueking.core.shiro.model;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.testng.annotations.Test;

public class UserTest {
    @Test
    public void testPassword2() {
        String algorithmName = "md5";
        String username = "yueking";
        String password = "123";

//        String salt1 = username;
//        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
//        int hashIterations = 2;

//        System.out.println("salt2:"+salt2);
        SimpleHash hash = new SimpleHash(algorithmName,password);
        String encodedPassword = hash.toHex();
        System.out.println("encodedPassword:1:"+encodedPassword);
        hash = new SimpleHash(algorithmName,encodedPassword);
        String encodedPassword2 = hash.toHex();
        System.out.println("encodedPassword:2:"+encodedPassword2);



    }


}