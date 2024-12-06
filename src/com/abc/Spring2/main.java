package com.abc.Spring2;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

@Test
public class main {
    public  void test() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        BookService bookService = ioc.getBean(BookService.class);
        bookService.save();
        PhoneService phoneService = ioc.getBean(PhoneService.class);
        phoneService.save();
    }
}
