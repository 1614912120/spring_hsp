package com.abc.spring.test;

import com.abc.spring.bean.Master;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class test {
    @Test
    public void getMuster() {
        //创建容器
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Object master = ioc.getBean("master");
        System.out.println(master);

        Master master1 = ioc.getBean("master", Master.class);
        System.out.println(master1);
    }


    //类加载路径
    @Test
    public void test() {
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);
    }
}
