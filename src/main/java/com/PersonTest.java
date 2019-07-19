package com;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersonTest {
    ApplicationContext spring;

    @BeforeMethod
    public void setUp() {
        spring = new ClassPathXmlApplicationContext("spring-beans.xml");
    }

    @Test
    public void testGetName() {
        Person person = (Person) spring.getBean("person");
        System.out.println("personName:"+person.getName());
        System.out.println("personName:"+person);

    }
}