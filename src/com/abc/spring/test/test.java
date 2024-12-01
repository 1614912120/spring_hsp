package com.abc.spring.test;

import com.abc.spring.abcApplicationContext.abcApplicationContext;
import com.abc.spring.bean.Master;
import com.abc.spring.service.MemberServiceImpl;
import org.dom4j.DocumentException;
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

    //测试容器
    @Test
    public void test2() throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        abcApplicationContext ioc = new abcApplicationContext("beans.xml");
        Master master = (Master) ioc.getBean("master");
        System.out.println("ioc=="+master);
    }


    //通过类型获取bean对象
    @Test
    public void getMonsterByType() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Master master = ioc.getBean(Master.class);
    }


    @Test
    public void test3() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        MemberServiceImpl memberServiceImpl = ioc.getBean("memberServiceImpl", MemberServiceImpl.class);
        memberServiceImpl.add();
    }

}
