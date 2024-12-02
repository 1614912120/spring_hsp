package com.abc.Spring1.annotation;

import jdk.nashorn.internal.ir.CallNode;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplicationContext context = new SpringApplicationContext(SpringConfig.class);
        ConcurrentHashMap<String, Object> ioc = context.getIoc();
        for (String key : ioc.keySet()) {
            System.out.println("0000000000");
            System.out.println("bean id="+key+"----"+"bean object="+ioc.get(key));
            System.out.println("0000000000");
        }
    }
}
