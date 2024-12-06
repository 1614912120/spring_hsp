package com.abc.proxy1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class main {
    public static void main(String[] args) {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        SmartAnimalable bean = ioc.getBean(SmartAnimalable.class);
        float sum = bean.getSum(101.0f, 11.2f);
        System.out.println("sum= " + sum);
    }
}
