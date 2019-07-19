package com.yueking.core.shiro.service;

import com.yueking.core.shiro.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class springTest {
    ApplicationContext spring;
    @BeforeMethod
    public void setUp() {
        spring = new ClassPathXmlApplicationContext("spring-beans.xml");
    }

    @Test
    public void testSpring(){
//        UserService userService = (UserService) spring.getBean("userService");
//        System.out.printf("===="+ userService);

    }
}